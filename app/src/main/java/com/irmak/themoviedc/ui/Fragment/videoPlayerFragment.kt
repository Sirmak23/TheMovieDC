package com.irmak.themoviedc.ui.Fragment

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.irmak.themoviedc.MainActivity
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.databinding.FragmentVideoPlayerBinding
import com.irmak.themoviedc.model.trailer.TrailerResponse
import com.irmak.themoviedc.repository.PopularMovieRepository
import com.irmak.themoviedc.repository.TrailerRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.viewModel.PopularMovieViewModel
import com.irmak.themoviedc.viewModel.TrailerViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.PopularMovieViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.TrailerViewModelFactory
import retrofit2.Retrofit


@Suppress("DEPRECATION")
class videoPlayerFragment : Fragment() {
    private lateinit var binding: FragmentVideoPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoPlayerBinding.inflate(inflater, container, false)

        binding.webView.webChromeClient = WebChromeClient()
        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.apply {
            javaScriptEnabled = true
            mediaPlaybackRequiresUserGesture = false
//        popularMovieViewModel.getPopularMovieDetail()
//        popularMovieViewModel.popularMovieDetailList.observe(
//            viewLifecycleOwner,
//            ::movieDetailObserver
//        )

//        binding.webView.settings.apply {
//            javaScriptEnabled = true
//
//            setSupportZoom(true)
//            builtInZoomControls = true
//            displayZoomControls = false
//            useWideViewPort = true
//            loadWithOverviewMode = true
//        }
            val animation = AnimationUtils.loadAnimation(
                requireContext(),
                com.irmak.themoviedc.R.anim.slide_in_right
            )
            binding.videoBackgorunds.startAnimation(animation)
            return binding.root
        }
    }

//override fun onDetach() {
//    super.onDetach()
//    binding.webView.onPause()
//    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
//}

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
        val main = activity as MainActivity
        main.setBottomNavigationViewVisibility(false)

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                binding.webView.onPause()
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                isEnabled = false
                requireActivity().onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
    }

//        requireActivity().onBackPressedDispatcher.addCallback(
//            viewLifecycleOwner,
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    binding.webView.onPause()
//                    val navOptions: NavOptions = NavOptions.Builder()
//                        .setPopUpTo(com.irmak.themoviedc.R.id.detailFragment, true).build()
//                    view.findNavController()
//                        .navigate(com.irmak.themoviedc.R.id.detailFragment, null, navOptions)
//                    activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
//                }
//
//            })


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
            Toast.makeText(requireContext(), "Film fragmanına erişilemiyor", Toast.LENGTH_SHORT)
                .show()
        } else {
            val iframe = """
    <html>
        <head>
            <style type="text/css">
                html, body {
                    margin: 0;
                    padding: 0;
                    overflow: hidden;
                }
                iframe {
                    position: absolute;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 100%;
                }
                    </style>
                </head>
                <body>
            <iframe width="100%" height="100%" src="https://www.youtube.com/embed/$video" frameborder="0" allowfullscreen autoplay ></iframe>
                    </body>
                 </html>
            """.trimIndent()

            binding.webView.settings.apply {
                javaScriptEnabled = true
                useWideViewPort = false
                loadWithOverviewMode = false
            }

            binding.webView.loadDataWithBaseURL(null, iframe, "text/html", "utf-8", null)

        }
    }

//    private fun movieDetailObserver(response: PopularMovieDetailResponse?) {
//        binding.videoBackgorunds.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response?.backdrop_path}")
//    }


}