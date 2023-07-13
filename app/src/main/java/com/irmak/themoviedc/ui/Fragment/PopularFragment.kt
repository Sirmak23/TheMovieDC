package com.irmak.themoviedc.ui.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.irmak.themoviedc.MainActivity
import com.irmak.themoviedc.adapter.HorizontalWrapperAdapter
import com.irmak.themoviedc.adapter.MovieAdapter
import com.irmak.themoviedc.adapter.NowPlayingAdapter
import com.irmak.themoviedc.adapter.NpHorizontalWrapperAdapter
import com.irmak.themoviedc.adapter.StoryAdapter
import com.irmak.themoviedc.adapter.TopRatedAdapter
import com.irmak.themoviedc.adapter.VideoAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.data.remote.api.movieIdNumber
import com.irmak.themoviedc.data.remote.api.pageNumber
import com.irmak.themoviedc.data.remote.api.pageNumberNpStory
import com.irmak.themoviedc.data.remote.api.pageNumberTopRated
import com.irmak.themoviedc.databinding.FragmentPopularBinding
import com.irmak.themoviedc.holder.frm
import com.irmak.themoviedc.model.nowPlayingModel.NowPlayingModel
import com.irmak.themoviedc.model.nowPlayingModel.ResultNP
import com.irmak.themoviedc.model.popularModel.MovieRespons
import com.irmak.themoviedc.model.storyModel.ResultStoryNP
import com.irmak.themoviedc.model.topRatedModel.topRatedResult
import com.irmak.themoviedc.model.trailer.TrailerResponse
import com.irmak.themoviedc.repository.NowPlayingRepository
import com.irmak.themoviedc.repository.PopularListRepository
import com.irmak.themoviedc.repository.StoryRepository
import com.irmak.themoviedc.repository.TopRatedRepository
import com.irmak.themoviedc.repository.TrailerRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.viewModel.ViewModelSub.MovieViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.NowPlayingViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.StoryViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.TopRatedViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.TrailerViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.MovieViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.NowPlayingViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.StoryViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.TopRatedViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.TrailerViewModelFactory
import retrofit2.Retrofit
import kotlin.math.abs
import kotlin.properties.Delegates

var isAutoScrollEnabledS = false

class PopularFragment : Fragment() {
    private lateinit var binding: FragmentPopularBinding
    private val autoScrollHandler = Handler()
    private val autoScrollHandlerS = Handler()
    private lateinit var autoScrollRunnable: Runnable
    private lateinit var autoScrollRunnableS: Runnable
    private var isAutoScrollEnabled = false

    var videoIds = ArrayList<Int?>()
    var videoIdsCheck = ArrayList<Int?>()
    var movieList: List<MovieRespons>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            movieAdapter.setList(ArrayList(newValue))
        }
        Log.e("Delegates", "user -> ${newValue}")
    }
    var topList: List<topRatedResult>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            topRatedAdapter.setTList(ArrayList(newValue))
        }
        Log.e("Delegates", "user -> ${newValue}")
    }
    var nowPlayList: List<ResultNP>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            nowPlayingAdapter.setNowPlayList(ArrayList(newValue))
        }
        Log.e("Delegates", "user -> ${newValue}")
    }
    var storyList: List<ResultStoryNP>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            storyAdapter.setStoryPlayList(ArrayList(newValue))
        }
        Log.e("Delegates", "user -> ${newValue}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularBinding.inflate(inflater, container, false)
        frm = "popular"
        ChoiceVideo = "movie"
        setupAutoScroll()
        setupAutoVideo()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        startAutoScroll()
        startAutoVideo()
    }

    override fun onPause() {
        super.onPause()
        stopAutoScroll()
        stopAutoVideo()
    }

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter()
    }
    private val horizontalAdapter: HorizontalWrapperAdapter by lazy {
        HorizontalWrapperAdapter(nowPlayingAdapter)
    }
    private val retrofit: Retrofit by lazy {
        RetrofitClient.getRetrofit()
    }
    private val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
    private val movieRepository: PopularListRepository by lazy {
        PopularListRepository(movieApi)
    }
    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory(movieRepository)
    }

    //now player
    private val nowPlayingAdapter: NowPlayingAdapter by lazy {
        NowPlayingAdapter()
    }
    private val nowPlayingRepository: NowPlayingRepository by lazy {
        NowPlayingRepository(movieApi)
    }
    private val nowPlayingViewModel: NowPlayingViewModel by viewModels {
        NowPlayingViewModelFactory(nowPlayingRepository)
    }

    // story adapter
    private val storyAdapter: StoryAdapter by lazy {
        StoryAdapter()
    }
    private val storyRepository: StoryRepository by lazy {
        StoryRepository(movieApi)
    }
    private val storyViewModel: StoryViewModel by viewModels {
        StoryViewModelFactory(storyRepository)
    }
    private val npHorizontalWrapperAdapter: NpHorizontalWrapperAdapter by lazy {
        NpHorizontalWrapperAdapter(storyAdapter)
    }

    // topRated
    private val topRatedAdapter: TopRatedAdapter by lazy {
        TopRatedAdapter()
    }
    private val topRatedRepository: TopRatedRepository by lazy {
        TopRatedRepository(movieApi)
    }
    private val topRatedViewModel: TopRatedViewModel by viewModels {
        TopRatedViewModelFactory(topRatedRepository)
    }
    private val trailerRepository: TrailerRepository by lazy {
        TrailerRepository(movieApi)
    }
    private val trailerViewModel: TrailerViewModel by viewModels {
        TrailerViewModelFactory(trailerRepository)
    }

    var video: String = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val main = activity as MainActivity
        main.setBottomNavigationViewVisibility(true)
        movieViewModel.getPopularList()
        nowPlayingViewModel.getNowPlayMovie()
        storyViewModel.getPlayVidoeMovie()
        topRatedViewModel.getTopRatedMovie()
        nowPlayingViewModel.nowPlaylist.observe(viewLifecycleOwner, ::getVideoIDs)
        trailerViewModel.trailerList.observe(viewLifecycleOwner, ::trailerObserve)
        initVide0NpBinding()
        initNpGRidBinding()
        observer()
        storyObserver()
        NowPlayObserver()
        observerTopRated()
        swipeToRefresh()
        upToPagePopular()
        backInvisible()
        initViewPager()
        setupTransformer()


    }

    var videoUr = ArrayList<String>()
    val videos = listOf("4SIITjPijKg", "N0S-PGgbu90", "tOAuJHu5Tg0")


    private fun getVideoIDs(result: NowPlayingModel) {
        videoIds.clear()
        for (x in 0..5) {
            val vID = result.results?.get(x)?.id
            videoIds.add(vID)
            Log.e("Delegates", "videoIds -> $videoIds")
        }
        getVideoKey()
    }

    private fun getVideoKey() {
        videoUr.clear()
        for (x in videoIds) {
            movieIdNumber = x
            trailerViewModel.getVideo()
            Log.e("Delegates", "abc -> ${x}")
            Log.e("movieIdNumber", "movieIdNumber -> ${movieIdNumber}")
        }
//        videoUr.clear()
    }

    fun trailerObserve(resp: TrailerResponse?) {
        if (resp != null && resp.results != null && resp.results.isNotEmpty()) {
            video = resp.results[0].key.toString()
            if (videoUr.contains(video)){
                return
            }else{
                videoUr.add(video)
            }
            Log.e("Delegates", "video -> ${video}")
            Log.e("videoUr", "videoUr -> ${videoUr}")
        }
        initweb()
    }

    private fun initweb() {
        with(binding) {
            recyclerWebview.apply {
                val videoAdapter = VideoAdapter(videoUr)
                adapter = videoAdapter
                }
            }
        }


    private fun swipeToRefresh() {
        binding.swiperefresh.setOnRefreshListener {
            pageNumberNpStory += 1
            storyViewModel.getPlayVidoeMovie()
            pageNumberTopRated += 1
            topRatedViewModel.getTopRatedMovie()
            binding.swiperefresh.isRefreshing = false
        }
    }


    private fun setupAutoScroll() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewNowPlaying.layoutManager = layoutManager

        autoScrollRunnable = Runnable {
            val currentPosition = layoutManager.findFirstVisibleItemPosition()
            val nextPosition = if (currentPosition == layoutManager.itemCount - 1) {
                pageNumber += 1
                movieViewModel.getPopularList()
                0
            } else {
                currentPosition + 1
            }
            binding.recyclerViewNowPlaying.smoothScrollToPosition(nextPosition)

            if (isAutoScrollEnabled) {
                autoScrollHandler.postDelayed(autoScrollRunnable, 5000) // 5 saniye
            }
        }
    }

    private fun setupAutoVideo() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerWebview.layoutManager = layoutManager

        autoScrollRunnableS = Runnable {
            val currentPosition = layoutManager.findFirstVisibleItemPosition()
            val nextPosition = if (currentPosition == layoutManager.itemCount - 1) {
//                pageNumber +=1
//                movieViewModel.getPopularList()
                0
            } else {
                currentPosition + 1
            }
            binding.recyclerWebview.smoothScrollToPosition(nextPosition)


            if (isAutoScrollEnabledS) {
                autoScrollHandlerS.postDelayed(autoScrollRunnableS, 150000) // 180 saniye
            }
        }
    }
//private fun setupAutoVideo() {
//    val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
//    binding.recyclerWebview.layoutManager = layoutManager
//
//    autoScrollRunnable2 = Runnable {
//        val currentPosition = layoutManager.findFirstVisibleItemPosition()
//        val nextPosition = if (currentPosition == 0) {
//            layoutManager.itemCount - 1
//        } else {
//            currentPosition - 1
//        }
//        binding.recyclerWebview.smoothScrollToPosition(nextPosition)
//
//        if (isAutoScrollEnabled2) {
//            autoScrollHandler2.postDelayed(autoScrollRunnable2, 5000) // 5 saniye
//        }
//    }
//}

    private fun startAutoScroll() {
        isAutoScrollEnabled = true
        autoScrollHandler.postDelayed(autoScrollRunnable, 5000) // 5 saniye
    }

    private fun startAutoVideo() {
        isAutoScrollEnabledS = true
        autoScrollHandlerS.postDelayed(autoScrollRunnableS, 150000) // 180 saniye
    }

    private fun stopAutoScroll() {
        isAutoScrollEnabled = false
        autoScrollHandler.removeCallbacks(autoScrollRunnable)
    }

    private fun stopAutoVideo() {
        isAutoScrollEnabledS = false
        autoScrollHandlerS.removeCallbacks(autoScrollRunnableS)
    }

    private fun updateApp() {
        val randomNum = (1..500).random()
        pageNumber = randomNum
    }

    private fun initVide0NpBinding() {
        with(binding) {
            recyclerStory.apply {
                storyAdapter.setStoryPlayList(ArrayList(storyList))
                adapter = storyAdapter
            }
        }
    }

    private fun initNpGRidBinding() {
        with(binding) {
            recyclerViewNowPlaying.apply {
                movieAdapter.setList(ArrayList(movieList))
                adapter = movieAdapter
            }
        }
    }

    private fun initViewPager() {
        with(binding) {
            topRatedAdapter.setTList(ArrayList(topList))
            viewPagerTopRated.adapter = topRatedAdapter
            viewPagerTopRated.offscreenPageLimit = 3
            viewPagerTopRated.clipToPadding = false
            viewPagerTopRated.clipChildren = false
            viewPagerTopRated.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        }
    }

    private fun setupTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.75f + r * 0.14f
        }
        binding.viewPagerTopRated.setPageTransformer(transformer)
    }

    private fun backInvisible() {
        with(binding) {
            if (binding.popularScroll.scrollY == 0) {
                upButtonPopular.visibility = View.GONE
            } else {
                upButtonPopular.visibility = View.VISIBLE
            }
        }
    }

    private fun observerTopRated() {
        topRatedViewModel.topList.observe(viewLifecycleOwner) { topList ->
//            Log.e("tag", "$movieList")
            this.topList = topList.results
        }
    }

    private fun observer() {
        movieViewModel.movieList.observe(viewLifecycleOwner) { movieList ->
//            Log.e("tag", "$movieList")
            this.movieList = movieList?.results
        }
    }

    private fun storyObserver() {
        storyViewModel.nowPlayVideolist.observe(viewLifecycleOwner) { storyList ->
            this.storyList = storyList.results
        }
    }

    private fun upToPagePopular() {
        binding.upButtonPopular.setOnClickListener {
            binding.popularScroll.smoothScrollTo(0, 0)
        }
    }

    private fun NowPlayObserver() {
        nowPlayingViewModel.nowPlaylist.observe(viewLifecycleOwner) { nowPlayList ->
            this.nowPlayList = nowPlayList.results
        }
    }

}

// concat kullanmak için
//    private fun initConcatBinding() {
//        with(binding) {
//            movieAdapter.setList(ArrayList(movieList))
//            nowPlayingAdapter.setNowPlayList(ArrayList(nowPlayList))
//            recyclerViewConcat.apply {
//                cAdapter = ConcatAdapter(
//                    horizontalAdapter,
//                    npHorizontalWrapperAdapter,
//                    GridConcatAdapter(movieAdapter)
//                )
//                recyclerViewConcat.layoutManager = LinearLayoutManager(requireContext())
//                adapter = cAdapter
//            }
//        }
//    }
// recylcer view buton geri gizleme
//    }private fun backInvisible() {
//        with(binding) {
//            upButtonPopular.visibility = View.GONE
//            val layoutManager: LinearLayoutManager =
//                recyclerViewNowPlaying.layoutManager as LinearLayoutManager
//            recyclerViewNowPlaying.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    if (layoutManager.findFirstVisibleItemPosition() == 0) {
//                        upButtonPopular.visibility = View.GONE
//                    } else {
//                        upButtonPopular.visibility = View.VISIBLE
//                    }
//                }
//            })
//        }
//    }
//    private fun AutoChange() {
//        val layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        binding.recyclerWebview.layoutManager = layoutManager
//        val timer = Timer()
//        val handler = Handler()
//        val runnable = object : Runnable {
//            override fun run() {
//                val currentPosition = layoutManager.findFirstVisibleItemPosition()
//                val nextPosition =
//                    if (currentPosition == layoutManager.itemCount - 1) {
//                        0
//                    } else currentPosition + 1
//                binding.recyclerWebview.smoothScrollToPosition(nextPosition)
////                    if (nextPosition == 0) {
////                        pageNumber += 1
////                    }
//                handler.postDelayed(this, 5000) // 5 saniye
//            }
//        }
//        timer.schedule(object : TimerTask() {
//            override fun run() {
//                handler.post(runnable)
//            }
//        }, 5000) // 5 saniye
//    }
