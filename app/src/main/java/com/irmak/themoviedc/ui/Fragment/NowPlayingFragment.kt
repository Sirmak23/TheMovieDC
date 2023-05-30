package com.irmak.themoviedc.ui.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.irmak.themoviedc.adapter.NowPlayingAdapter
import com.irmak.themoviedc.adapter.StoryAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.databinding.FragmentNowPlayingBinding
import com.irmak.themoviedc.model.nowPlayingModel.ResultNP
import com.irmak.themoviedc.model.storyModel.ResultStoryNP
import com.irmak.themoviedc.repository.NowPlayingRepository
import com.irmak.themoviedc.repository.StoryRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.viewModel.NowPlayingViewModel
import com.irmak.themoviedc.viewModel.StoryViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.NowPlayingViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.StoryViewModelFactory
import retrofit2.Retrofit
import java.util.*
import kotlin.properties.Delegates

class NowPlayingFragment : Fragment() {
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
    private val storyAdapter:StoryAdapter by lazy {
        StoryAdapter()
    }
    private val storyRepository: StoryRepository by lazy {
        StoryRepository(movieApi)
    }
    private val storyViewModel: StoryViewModel by viewModels {
        StoryViewModelFactory(storyRepository)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nowPlayingViewModel.getNowPlayMovie()
        storyViewModel.getPlayVidoeMovie()
        initNpBinding()
        initVideNpBinding()
        NowPlayObserver()
        storyObserver()
        upToPage()


//        initNpREfBinding()
//        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        binding.recyclerViewNowPlayingBackRefresh.layoutManager = layoutManager
//
//        val timer = Timer()
//        val handler = Handler()
//
//        val runnable = object : Runnable {
//            override fun run() {
//                val currentPosition = layoutManager.findFirstVisibleItemPosition()
//                val nextPosition =
//                    if (currentPosition == layoutManager.itemCount - 1) 0 else currentPosition + 1
//                binding.recyclerViewNowPlayingBackRefresh.smoothScrollToPosition(nextPosition)
//                handler.postDelayed(this, 5000) // 5 saniye
//            }
//        }
//        timer.schedule(object : TimerTask() {
//            override fun run() {
//                handler.post(runnable)
//
//            }
//        }, 5000) // 5 sani
    }

    private fun initNpBinding() {
        with(binding) {
            recyclerViewNowPlaying.apply {
                nowPlayingAdapter.setNowPlayList(ArrayList(nowPlayList))
                adapter = nowPlayingAdapter
                recyclerViewNowPlaying.layoutManager = GridLayoutManager(requireContext(), 1)
            }
        }
    }
    private fun initVideNpBinding() {
        with(binding) {
            recyclerViewTrailer.apply {
                storyAdapter.setStoryPlayList(ArrayList(storyList))
                adapter = storyAdapter
            }
        }
    }

    //    private fun initNpREfBinding() {
//            with(binding) {
//                recyclerViewNowPlayingBackRefresh.apply {
//                    nowPlayingAdapter.setNowPlayList(ArrayList(nowPlayList))
//                    adapter = nowPlayingAdapter
//                    recyclerViewNowPlayingBackRefresh.layoutManager = GridLayoutManager(requireContext(), 1)
//                }
//            }
//        }
    private fun NowPlayObserver() {
        nowPlayingViewModel.nowPlaylist.observe(viewLifecycleOwner) { nowPlayList ->
            this.nowPlayList = nowPlayList.results
        }
    }
    private fun storyObserver(){
        storyViewModel.nowPlayVideolist.observe(viewLifecycleOwner){storyList->
            this.storyList = storyList.results
        }
    }
    private fun upToPage() {
        binding.upButton.setOnClickListener {
            binding.recyclerViewNowPlaying.smoothScrollToPosition(0)
        }
    }

}




