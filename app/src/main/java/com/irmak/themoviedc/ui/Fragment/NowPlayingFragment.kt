package com.irmak.themoviedc.ui.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.MainActivity
import com.irmak.themoviedc.adapter.NowPlayingAdapter
import com.irmak.themoviedc.adapter.NpHorizontalWrapperAdapter
import com.irmak.themoviedc.adapter.StoryAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.databinding.FragmentNowPlayingBinding
import com.irmak.themoviedc.holder.frm
import com.irmak.themoviedc.model.nowPlayingModel.ResultNP
import com.irmak.themoviedc.model.storyModel.ResultStoryNP
import com.irmak.themoviedc.repository.NowPlayingRepository
import com.irmak.themoviedc.repository.StoryRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.viewModel.ViewModelSub.NowPlayingViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.StoryViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.NowPlayingViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.StoryViewModelFactory
import retrofit2.Retrofit
import kotlin.properties.Delegates

class NowPlayingFragment : Fragment() {
    lateinit var concat: ConcatAdapter
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

    private lateinit var binding: FragmentNowPlayingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        frm = "nowPlaying"
        val main = activity as MainActivity
        main.setBottomNavigationViewVisibility(true)
        return binding.root
    }

    private val nowPlayingAdapter: NowPlayingAdapter by lazy {
        NowPlayingAdapter()
    }
    private val nowPlayingRepository: NowPlayingRepository by lazy {
        NowPlayingRepository(movieApi)
    }
    private val retrofit: Retrofit by lazy {
        RetrofitClient.getRetrofit()
    }
    private val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
    private val nowPlayingViewModel: NowPlayingViewModel by viewModels {
        NowPlayingViewModelFactory(nowPlayingRepository)
    }
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nowPlayingViewModel.getNowPlayMovie()
        storyViewModel.getPlayVidoeMovie()
        initConcatAdapter()
        NowPlayObserver()
        storyObserver()
        upToPage()
        upToPagePopular()
        backInvisible()

    }

    private fun initConcatAdapter() {
        with(binding) {
            nowPlayingAdapter.setNowPlayList(ArrayList(nowPlayList))
            storyAdapter.setStoryPlayList(ArrayList(storyList))
            recyclerViewNpConcat.apply {
                concat = ConcatAdapter(
                    npHorizontalWrapperAdapter,
                    nowPlayingAdapter
                )
                recyclerViewNpConcat.layoutManager = LinearLayoutManager(requireContext())
                adapter = concat
            }
        }
    }

    private fun NowPlayObserver() {
        nowPlayingViewModel.nowPlaylist.observe(viewLifecycleOwner) { nowPlayList ->
            this.nowPlayList = nowPlayList.results
        }
    }

    private fun storyObserver() {
        storyViewModel.nowPlayVideolist.observe(viewLifecycleOwner) { storyList ->
            this.storyList = storyList.results
        }
    }

    private fun upToPage() {
        binding.upButton.setOnClickListener {
            binding.recyclerViewNpConcat.smoothScrollToPosition(0)
        }
    }

    private fun backInvisible() {
        with(binding) {
            upButton.visibility = View.GONE
            val layoutManager: LinearLayoutManager =
                recyclerViewNpConcat.layoutManager as LinearLayoutManager
            recyclerViewNpConcat.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (layoutManager.findFirstVisibleItemPosition() == 0) {
                        upButton.visibility = View.GONE
                    } else {
                        upButton.visibility = View.VISIBLE
                    }
                }
            })
        }
    }

    private fun upToPagePopular() {
        binding.upButton.setOnClickListener {
            binding.recyclerViewNpConcat.smoothScrollToPosition(0)
        }
    }
}


//    private fun initNpBinding() {
//        with(binding) {
//            recyclerViewNowPlaying.apply {
//                nowPlayingAdapter.setNowPlayList(ArrayList(nowPlayList))
//                adapter = nowPlayingAdapter
//                recyclerViewNowPlaying.layoutManager = GridLayoutManager(requireContext(), 1)
//            }
//        }
//    }
//    private fun initVideNpBinding() {
//        with(binding) {
//            recyclerViewTrailer.apply {
//                storyAdapter.setStoryPlayList(ArrayList(storyList))
//                adapter = storyAdapter
//            }
//        }
//    }
