package com.irmak.themoviedc.ui.Fragment

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.irmak.themoviedc.MainActivity
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.databinding.FragmentVideoPlayerBinding
import com.irmak.themoviedc.model.popularModel.PopularMovieDetailResponse
import com.irmak.themoviedc.model.trailer.TrailerResponse
import com.irmak.themoviedc.model.trailer.TvTrailerResponse
import com.irmak.themoviedc.model.tvDetailModel.TvDetailModel
import com.irmak.themoviedc.repository.*
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.ui.extensions.loadImage
import com.irmak.themoviedc.viewModel.*
import com.irmak.themoviedc.viewModel.viewModelFactory.*
import retrofit2.Retrofit

var ChoiceVideo: String = "null"

@Suppress("DEPRECATION")
class videoPlayerFragment : Fragment() {
    private lateinit var binding: FragmentVideoPlayerBinding

    @SuppressLint("SetJavaScriptEnabled")
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

            val webView = binding.webView
            val progressBar = binding.progressBar

            webView.settings.javaScriptEnabled = true
            webView.webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView, newProgress: Int) {
                    progressBar.progress = newProgress
                    if (newProgress == 100) {
                        progressBar.visibility = ProgressBar.INVISIBLE
                        webView.visibility = WebView.VISIBLE
                    } else {
                        progressBar.visibility = ProgressBar.VISIBLE
                    }
                }
            }

            webView.webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    progressBar.visibility = ProgressBar.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility = ProgressBar.INVISIBLE
                }
            }

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
//            binding.movieTitleTextView.visibility = View.VISIBLE
            val myImageView = binding.videoBackgorunds

            val fadeInAnimation = AlphaAnimation(0f, 1f)
            fadeInAnimation.duration = 1000
            fadeInAnimation.fillAfter = true
            fadeInAnimation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {}
                override fun onAnimationRepeat(animation: Animation?) {}

            })

            myImageView.startAnimation(fadeInAnimation)
            myImageView.visibility = ImageView.VISIBLE
        }
        return binding.root
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
    private val tvTrailerRepository: TvTrailerRepository by lazy {
        TvTrailerRepository(movieApi)
    }
    private val TvTrailerViewModel: TvTrailerViewModel by viewModels {
        TvTrailerViewModelFactory(tvTrailerRepository)
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
    private val tvDetailRepository: TvDetailRepository by lazy {
        TvDetailRepository(movieApi)
    }
    private val tvDetailViewModel: TvDetailViewModel by viewModels {
        TvDetailViewModelFactory(tvDetailRepository)
    }

    var video: String = "null"
    var tvVideo: String = "null"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trailerViewModel.getVideo()
        TvTrailerViewModel.getTvVideo()
        trailerViewModel.trailerList.observe(viewLifecycleOwner, ::trailerObserve)
        TvTrailerViewModel.tvTrailerList.observe(viewLifecycleOwner, ::tvTrailerObserve)
        popularMovieViewModel.getPopularMovieDetail()
        tvDetailViewModel.getTvDetail()
        if (ChoiceVideo == "movie") {
            popularMovieViewModel.popularMovieDetailList.observe(
                viewLifecycleOwner,
                ::movieDetailObserver
            )
        } else if (ChoiceVideo == "tv") {
            tvDetailViewModel.tvDetailList.observe(viewLifecycleOwner, ::tvDetailObserver)
        }

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
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
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


    private fun movieDetailObserver(response: PopularMovieDetailResponse?) {
        binding.videoBackgorunds.loadImage("https://image.tmdb.org/t/p/w1066_and_h600_bestv2${response?.backdrop_path}")

    }

    private fun tvDetailObserver(response: TvDetailModel) {
        binding.videoBackgorunds.loadImage("https://image.tmdb.org/t/p/w1066_and_h600_bestv2${response.backdrop_path}")

    }

    fun trailerObserve(resp: TrailerResponse?) {
        if (resp != null && resp.results != null && resp.results.isNotEmpty()) {
            video = resp.results[0].key?.toString() ?: ""
            if (ChoiceVideo == "movie") {
                updateWebView()
            }
        } else {
            // Hata durumunda yapılacak işlemler
        }
    }

    fun tvTrailerObserve(resp: TvTrailerResponse?) {
        if (resp != null && resp.results != null && resp.results.isNotEmpty()) {
            tvVideo = resp.results[0].key.toString()
            if (ChoiceVideo == "tv") {
                updateTvWebView()
            }
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

    private fun updateTvWebView() {
        if (tvVideo == "null") {
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
            <iframe width="100%" height="100%" src="https://www.youtube.com/embed/$tvVideo" frameborder="0" allowfullscreen autoplay ></iframe>
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