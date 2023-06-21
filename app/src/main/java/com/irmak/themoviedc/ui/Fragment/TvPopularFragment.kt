package com.irmak.themoviedc.ui.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.irmak.themoviedc.adapter.TvPopularAdapter
import com.irmak.themoviedc.adapter.TvTopRatedAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.databinding.FragmentTvPopularBinding
import com.irmak.themoviedc.model.tvPopularModel.TvPopularResponse
import com.irmak.themoviedc.model.tvTopRatedModel.TvTopRatedResponse
import com.irmak.themoviedc.repository.TvPopularRepository
import com.irmak.themoviedc.repository.TvRatedRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.viewModel.TvPopularViewModel
import com.irmak.themoviedc.viewModel.TvRatedViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.TvPopularViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.TvRatedViewModelFactory
import retrofit2.Retrofit
import kotlin.math.abs
import kotlin.properties.Delegates

class TvPopularFragment : Fragment() {
    lateinit var binding: FragmentTvPopularBinding

    var tvList: List<TvPopularResponse>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            tvPopularAdapter.setListTv(ArrayList(newValue))
        }
        Log.e("Delegates", "user -> ${newValue}")
    }
    var tvRateList: List<TvTopRatedResponse>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
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
        return binding.root
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
        tvPopularViewModel.getPopularTv()
        tvRatedViewModel.getTvRated()
        initViewPager()
        initViewPagerRate()
        tvPopularObserve()
        tvRatedObserve()
        setupTransformer()
    }

    private fun initViewPager() {
        with(binding) {
            viewPagerTv.apply {
                tvPopularAdapter.setListTv(ArrayList(tvList))
                adapter = tvPopularAdapter
                offscreenPageLimit = 3
                clipToPadding = false
                clipChildren = false
                getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            }
        }
    }
    private fun initViewPagerRate(){
        with(binding){
            recyclerViewTvRate.apply {
                tvRatedAdapter.setListRateTv(ArrayList(tvRateList))
                adapter = tvRatedAdapter
                layoutManager = GridLayoutManager(requireContext(), 2)

            }
        }
    }

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
        }
    }
    private fun tvRatedObserve() {
        tvRatedViewModel.TvRatedList.observe(viewLifecycleOwner) { tvRateList ->
            this.tvRateList = tvRateList.results
        }
    }

}