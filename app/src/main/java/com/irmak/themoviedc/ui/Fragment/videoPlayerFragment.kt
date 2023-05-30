package com.irmak.themoviedc.ui.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import com.irmak.themoviedc.R
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.databinding.FragmentVideoPlayerBinding
import com.irmak.themoviedc.model.popularModel.PopularMovieDetailResponse
import com.irmak.themoviedc.model.trailer.TrailerResponse
import com.irmak.themoviedc.repository.PopularMovieRepository
import com.irmak.themoviedc.repository.TrailerRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.ui.extensions.loadImage
import com.irmak.themoviedc.viewModel.PopularMovieViewModel
import com.irmak.themoviedc.viewModel.TrailerViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.PopularMovieViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.TrailerViewModelFactory
import retrofit2.Retrofit

class videoPlayerFragment : Fragment() {
private lateinit var binding: FragmentVideoPlayerBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoPlayerBinding.inflate(inflater,container,false)
        popularMovieViewModel.getPopularMovieDetail()
        popularMovieViewModel.popularMovieDetailList.observe(
            viewLifecycleOwner,
            ::movieDetailObserver
        )
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_down)
        binding.videoBackgorunds.startAnimation(animation)
        return binding.root
    }

    private val trailerRepository: TrailerRepository by lazy {
        TrailerRepository(movieApi)
    }
    private val trailerViewModel: TrailerViewModel by viewModels {
        TrailerViewModelFactory(trailerRepository)
    }
    private val retrofit: Retrofit by lazy {
        RetrofitClient.getRetrofit()
    }
    private val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
    private val popularMovieRepository: PopularMovieRepository by lazy {
        PopularMovieRepository(movieApi)
    }
    private val popularMovieViewModel: PopularMovieViewModel by viewModels {
        PopularMovieViewModelFactory(popularMovieRepository)
    }
    var video: String = "null"
    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trailerViewModel.getVideo()
        trailerViewModel.trailerList.observe(viewLifecycleOwner, ::trailerObserve)
    }
    fun trailerObserve(resp: TrailerResponse?) {
        if (resp != null && resp.results != null && resp.results.isNotEmpty()) {
            video = resp.results[0].key?.toString() ?: ""
            updateWebView()
        } else {
            // Hata durumunda yapılacak işlemler
        }
    }

    private fun updateWebView() {
        if (video == "null") {
            Toast.makeText(requireContext(), "Film fragmanına erişilemiyor", Toast.LENGTH_SHORT).show()
        } else {
            binding.webView.settings.javaScriptEnabled = true
            val iframe = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/$video\" frameborder=\"0\" allowfullscreen></iframe>"
            binding.webView.loadData(iframe, "text/html", "utf-8")
        }
    }
    private fun movieDetailObserver(response: PopularMovieDetailResponse?) {
        binding.videoBackgorunds.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response?.backdrop_path}")
    }


}