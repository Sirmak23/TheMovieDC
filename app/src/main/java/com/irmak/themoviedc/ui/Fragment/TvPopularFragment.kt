package com.irmak.themoviedc.ui.Fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.irmak.themoviedc.MainActivity
import com.irmak.themoviedc.adapter.TvPopularAdapter
import com.irmak.themoviedc.adapter.TvTopRatedAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.data.remote.api.tvPopuplarPageNo
import com.irmak.themoviedc.data.remote.api.tvTopRatedPageNo
import com.irmak.themoviedc.databinding.FragmentTvPopularBinding
import com.irmak.themoviedc.model.tvPopularModel.TvPopularResponse
import com.irmak.themoviedc.model.tvTopRatedModel.TvTopRatedModel
import com.irmak.themoviedc.model.tvTopRatedModel.TvTopRatedResponse
import com.irmak.themoviedc.repository.TvPopularRepository
import com.irmak.themoviedc.repository.TvRatedRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.ui.extensions.isBackOrNext
import com.irmak.themoviedc.viewModel.ViewModelSub.TvPopularViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.TvRatedViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.TvPopularViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.TvRatedViewModelFactory
import retrofit2.Retrofit
import kotlin.math.abs
import kotlin.properties.Delegates

class TvPopularFragment : Fragment() {
    lateinit var binding: FragmentTvPopularBinding
    var tvList: List<TvPopularResponse>? by Delegates.observable(
        arrayListOf()
    ) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            tvPopularAdapter.setListTv(ArrayList(newValue))
        }
        Log.e("Delegates", "user -> ${newValue}")
    }
    var tvRateList: List<TvTopRatedResponse>? by Delegates.observable(
        arrayListOf()
    ) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            tvRatedAdapter.setListRateTv(ArrayList(newValue))
        }
        Log.e("Delegates", "user -> ${newValue}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvPopularBinding.inflate(inflater, container, false)
        val main = activity as MainActivity
        main.setBottomNavigationViewVisibility(true)
        Log.e("info", "oncreateview")
        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("info", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("info", "onCreate")
        isBackOrNext = 0
        tvPopularViewModel.getPopularTv()
        tvRatedViewModel.getTvRated()

    }


    override fun onStart() {
        super.onStart()
        Log.e("info", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("info", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("info", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("info", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("info", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("info", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("info", "onDetach")
    }


    private val tvPopularAdapter: TvPopularAdapter by lazy {
        TvPopularAdapter()
    }
    private val retrofit: Retrofit by lazy {
        RetrofitClient.getRetrofit()
    }
    private val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
    private val tvPopularRepository: TvPopularRepository by lazy {
        TvPopularRepository(movieApi)
    }
    private val tvPopularViewModel: TvPopularViewModel by viewModels {
        TvPopularViewModelFactory(tvPopularRepository)
    }

    // topRated
    private val tvRatedAdapter: TvTopRatedAdapter by lazy {
        TvTopRatedAdapter()
    }
    private val tvRatedRepository: TvRatedRepository by lazy {
        TvRatedRepository(movieApi)
    }
    private val tvRatedViewModel: TvRatedViewModel by viewModels {
        TvRatedViewModelFactory(tvRatedRepository)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.upButtonPop.visibility = View.GONE
        tvRatedViewModel.tvRatedList.observe(viewLifecycleOwner, ::setPageNo)
        initViewPager()
        initRate()
        tvPopularObserve()
        tvRatedObserve()
        setupTransformer()
        // swipeToRefresh()
//        MoreTvRated()
        tapToUp()
        MoreTvPopular()
        Log.e("info", "onViewCreated")

    }

    @SuppressLint("SetTextI18n")
    private fun setPageNo(response: TvTopRatedModel) {
        val nowPage = response.page
        val totalPages = response.total_pages
        binding.pageEditText.text = "$nowPage/$totalPages"

        binding.backPageButton.setOnClickListener {
            if (nowPage != null) {
                if (nowPage > 3) {
                        isBackOrNext = 2
                        tvRatedViewModel.getTvRated()
                    binding.recyclerViewTvRate.smoothScrollToPosition(0)
                    }

                }
            }

                binding.nextPageButton.setOnClickListener {
                        isBackOrNext = 1
                        tvRatedViewModel.getTvRated()
                    binding.recyclerViewTvRate.smoothScrollToPosition(0)
                }
            }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("info", "onActivityCreated")

    }


    private fun initViewPager() {
        with(binding) {
            viewPagerTv.apply {
//                tvPopularAdapter.setListTv(ArrayList(tvList))
                adapter = tvPopularAdapter
                offscreenPageLimit = 3
                clipToPadding = false
                clipChildren = false
                getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            }
        }
    }


    private fun initRate() {
        with(binding) {
            recyclerViewTvRate.apply {
//                tvRatedAdapter.setListRateTv(ArrayList(tvRateList))
                adapter = tvRatedAdapter
                layoutManager = GridLayoutManager(requireContext(), 3)
            }
        }
    }


//    private fun swipeToRefresh() {
//        binding.swiperefreshTv.setOnRefreshListener {
//            tvPopuplarPageNo += 1
//            tvPopularViewModel.getPopularTv()
//            binding.viewPagerTv.setCurrentItem(0, true)
//            binding.swiperefreshTv.isRefreshing = false
//        }
//    }

    private fun setupTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.75f + r * 0.14f
        }
        binding.viewPagerTv.setPageTransformer(transformer)
    }

    private fun tvPopularObserve() {
        tvPopularViewModel.tvList.observe(viewLifecycleOwner) { tvList ->
            this.tvList = tvList.results
            if (tvList?.results.isNullOrEmpty()){
                tvPopuplarPageNo++
                tvPopularViewModel.getPopularTv()
            }

        }
    }

    private fun tvRatedObserve() {
        tvRatedViewModel.tvRatedList.observe(viewLifecycleOwner) { tvRateList ->
            this.tvRateList = tvRateList.results
        }
    }


    private fun MoreTvRated() {
        with(binding) {
            popularScroll.viewTreeObserver.addOnScrollChangedListener {
                val view = popularScroll.getChildAt(popularScroll.childCount - 1)
                val diff = (view.bottom - (popularScroll.height + popularScroll.scrollY))

                if (diff <= 0) {
                    tvTopRatedPageNo += 1
                    tvRatedViewModel.getTvRated()
//                    upButtonTvPopular.visibility = View.VISIBLE
                } else {
//                    upButtonTvPopular.visibility = View.GONE
                }
            }

        }
    }


    private fun tapToUp() {
        with(binding) {
            popularScroll.viewTreeObserver.addOnScrollChangedListener {
                val diff = popularScroll.scrollY

                if (diff > 0) {
                    upButtonPop.visibility = View.VISIBLE
                } else {
                    upButtonPop.visibility = View.GONE
                }
            }
            upButtonPop.setOnClickListener {
                popularScroll.smoothScrollTo(0, 0)
            }
        }

    }

    private fun MoreTvPopular() {
        with(binding) {
            viewPagerTv.viewTreeObserver.addOnScrollChangedListener {
                val view = viewPagerTv.getChildAt(viewPagerTv.childCount - 1)
                val diff = (view.right - (viewPagerTv.width + viewPagerTv.scrollX))

                if (diff <= 0) {
                    val lastPageIndex = viewPagerTv.adapter?.itemCount?.minus(1) ?: 0
                    val currentPageIndex = viewPagerTv.currentItem
                    if (currentPageIndex == lastPageIndex) {
                        tvPopuplarPageNo += 1
                        tvPopularViewModel.getPopularTv()

                    }
                } else {
                    // upButtonTvPopular.visibility = View.GONE
                }
            }
        }
    }
}