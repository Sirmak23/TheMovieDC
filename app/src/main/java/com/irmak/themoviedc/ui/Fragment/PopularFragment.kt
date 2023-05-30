package com.irmak.themoviedc.ui.Fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.irmak.themoviedc.MainActivity
import com.irmak.themoviedc.R
import com.irmak.themoviedc.adapter.MovieAdapter
import com.irmak.themoviedc.adapter.NowPlayingAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.data.remote.api.movieIdNumber
import com.irmak.themoviedc.data.remote.api.pageNumber
import com.irmak.themoviedc.databinding.FragmentPopularBinding
import com.irmak.themoviedc.model.nowPlayingModel.ResultNP
import com.irmak.themoviedc.model.popularModel.MovieRespons
import com.irmak.themoviedc.model.popularModel.PopularMovieDetailResponse
import com.irmak.themoviedc.model.popularModel.PopularMovieResponse
import com.irmak.themoviedc.repository.NowPlayingRepository
import com.irmak.themoviedc.repository.PopularListRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.ui.extensions.loadImage
import com.irmak.themoviedc.viewModel.MovieViewModel
import com.irmak.themoviedc.viewModel.NowPlayingViewModel
import com.irmak.themoviedc.viewModel.PopularMovieViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.MovieViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.NowPlayingViewModelFactory
import retrofit2.Retrofit
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.fixedRateTimer
import kotlin.properties.Delegates
import kotlin.system.exitProcess

class PopularFragment : Fragment() {

    var movieList: List<MovieRespons>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            movieAdapter.setList(ArrayList(newValue))
        }
        Log.e("Delegates", "user -> ${newValue}")
    }

    var nowPlayList: List<ResultNP>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            nowPlayingAdapter.setNowPlayList(java.util.ArrayList(newValue))
        }
        Log.e("Delegates", "user -> ${newValue}")
    }

    private lateinit var binding: FragmentPopularBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularBinding.inflate(inflater,container,false)
        return binding.root
    }

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val main = activity as MainActivity
        main.setBottomNavigationViewVisibility(true)
        movieViewModel.getPopularList()
        nowPlayingViewModel.getNowPlayMovie()
        initBinding()
        observer()
        NowPlayObserver()
        swipeToRefresh()
        upToPagePopular()
        initNpRefreshBinding()
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.popularSwipeRecyler.layoutManager = layoutManager
        val timer = Timer()
        val handler = Handler()

        val runnable = object : Runnable {
            override fun run() {
                val currentPosition = layoutManager.findFirstVisibleItemPosition()
                val nextPosition =
                    if (currentPosition == layoutManager.itemCount - 1) 0 else currentPosition + 1
                binding.popularSwipeRecyler.smoothScrollToPosition(nextPosition)
                handler.postDelayed(this, 7000) // 5 saniye
            }
        }
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }
        }, 7000) // 5 sani
    }

    private fun swipeToRefresh() {
        binding.swiperefresh.setOnRefreshListener {
            updateApp()
            movieViewModel.getPopularList()
            binding.swiperefresh.isRefreshing = false
        }
    }
    private fun updateApp() {
        val randomNum = (1..500).random()
        pageNumber = randomNum
    }
    private fun initBinding() {
        with(binding) {
            recyclerViewer.apply {
                movieAdapter.setList(ArrayList(movieList))
                adapter = movieAdapter
                recyclerViewer.layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
    }
    private fun initNpRefreshBinding() {
        with(binding) {
            popularSwipeRecyler.apply {
                nowPlayingAdapter.setNowPlayList(ArrayList(nowPlayList))
                adapter = nowPlayingAdapter
                popularSwipeRecyler.layoutManager = GridLayoutManager(requireContext(), 1)
            }
        }
    }

    private fun observer() {
        movieViewModel.movieList.observe(viewLifecycleOwner) { movieList ->
//            Log.e("tag", "$movieList")
            this.movieList = movieList?.results
        }
    }
    private fun upToPagePopular(){
        binding.upButtonPopular.setOnClickListener{
            binding.recyclerViewer.smoothScrollToPosition(0)
            binding.nestedScrollView.smoothScrollTo(0,0)

        }
    }
    private fun NowPlayObserver() {
        nowPlayingViewModel.nowPlaylist.observe(viewLifecycleOwner) { nowPlayList ->
            this.nowPlayList = nowPlayList.results
        }
    }


}