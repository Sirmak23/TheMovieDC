package com.irmak.themoviedc.ui.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.irmak.themoviedc.MainActivity
import com.irmak.themoviedc.R
import com.irmak.themoviedc.adapter.ActorAdapter
import com.irmak.themoviedc.adapter.PopularMovieDetailAdapter
import com.irmak.themoviedc.adapter.WatchProviderAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.databinding.FragmentDetailBinding
import com.irmak.themoviedc.model.actorModel.ActorDetail
import com.irmak.themoviedc.model.popularModel.PopularMovieDetailResponse
import com.irmak.themoviedc.model.trailer.TrailerResponse
import com.irmak.themoviedc.model.watchProviders.Provider
import com.irmak.themoviedc.repository.ActorRepository
import com.irmak.themoviedc.repository.PopularMovieRepository
import com.irmak.themoviedc.repository.TrailerRepository
import com.irmak.themoviedc.repository.WatchProviderRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.ui.extensions.loadImage
import com.irmak.themoviedc.viewModel.ViewModelSub.ActorViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.PopularMovieViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.TrailerViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.WatchProvideViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.ActorViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.PopularMovieViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.TrailerViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.WatchProvideViewModelFactory
import retrofit2.Retrofit
import kotlin.properties.Delegates


class DetailFragment : Fragment() {
    var actorList: List<ActorDetail>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            actorAdapter.setActorList(ArrayList(newValue))
        }
        Log.e("Delegates", "user -> ${newValue}")
    }
    var providerList: List<Provider>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            watchProviderAdapter.setProvidersList(ArrayList(newValue))
        }
        Log.e("Delegates", "providerList -> ${newValue}")
    }

    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        popularMovieViewModel.getPopularMovieDetail()
        popularMovieViewModel.popularMovieDetailList.observe(
            viewLifecycleOwner,
            ::movieDetailObserver
        )
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_down)
        binding.popularDetailBack.startAnimation(animation)
        ChoiceVideo = "movie"
        return binding.root
    }

    private val trailerRepository: TrailerRepository by lazy {
        TrailerRepository(movieApi)
    }
    private val trailerViewModel: TrailerViewModel by viewModels {
        TrailerViewModelFactory(trailerRepository)
    }

    private val actorAdapter: ActorAdapter by lazy {
        ActorAdapter()
    }
    private val retrofit: Retrofit by lazy {
        RetrofitClient.getRetrofit()
    }
    private val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
    private val actorRepository: ActorRepository by lazy {
        ActorRepository(movieApi)
    }
    private val actorViewModel: ActorViewModel by viewModels {
        ActorViewModelFactory(actorRepository)
    }
    private val popularMovieDetailAdapter: PopularMovieDetailAdapter by lazy {
        PopularMovieDetailAdapter()
    }
    private val popularMovieRepository: PopularMovieRepository by lazy {
        PopularMovieRepository(movieApi)
    }
    private val popularMovieViewModel: PopularMovieViewModel by viewModels {
        PopularMovieViewModelFactory(popularMovieRepository)
    }
    private val watchProviderRepository:WatchProviderRepository by lazy {
        WatchProviderRepository(movieApi)
    }
    private val watchProvideViewModel:WatchProvideViewModel by viewModels {
        WatchProvideViewModelFactory(watchProviderRepository)
    }
    private val watchProviderAdapter:WatchProviderAdapter by lazy {
        WatchProviderAdapter()
    }
    var video: String = "null"
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val main = activity as MainActivity
        main.setBottomNavigationViewVisibility(false)
        actorViewModel.getActorDetail()
        providerInitBinding()
        actorInitBinding()
        providerObserver()
        ActorObserver()
        trailerViewModel.getVideo()
        watchProvideViewModel.getProviders()
        binding.backButtonImageView.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToPopularFragment())
        }
        binding.trailerButton.setOnClickListener {
            if (video == "null") {
                Toast.makeText(requireContext(), "Film fragmanına erişilemiyor", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val action = DetailFragmentDirections.actionDetailFragmentToVideoPlayerFragment()
                    findNavController().navigate(action)
                }
            }

        trailerViewModel.trailerList.observe(viewLifecycleOwner, ::trailerObserve)

    }

    fun trailerObserve(resp: TrailerResponse?) {
//         video = resp?.results?.get(0)?.let { it.key.toString() } ?:"null"
        if (resp != null && resp.results != null && resp.results.isNotEmpty()) {
            video = resp.results.get(0).key?.toString() ?: ""

        } else {

        }
    }

    @SuppressLint("SetTextI18n")
    private fun movieDetailObserver(response: PopularMovieDetailResponse?) {
        binding.MovieDetailNameTextView.text = response?.title
        binding.imdbDetailTextView.text = "${response?.vote_average}/10"
        binding.DetailYt.text = "Y.T: ${response?.release_date}"
        binding.DetailOzetTExt.text = response?.overview
        binding.movieDEtailImageView.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response?.poster_path}")
        binding.popularDetailBack.loadImage("https://image.tmdb.org/t/p/w300_and_h450_bestv2${response?.backdrop_path}")
//        binding.popularDetailBack.loadImage("https://www.themoviedb.org/t/p/w533_and_h300_bestv2${response?.backdrop_path}")
        binding.imdbDetailPhoto2.loadImage("https://ia.media-imdb.com/images/M/MV5BODc4MTA3NjkzNl5BMl5BcG5nXkFtZTgwMDg0MzQ2OTE@._V1_.png")
    }

    private fun actorInitBinding() {
        with(binding) {
            ActorRecyler.apply {
                actorAdapter.setActorList(ArrayList(actorList))
                adapter = actorAdapter
                ActorRecyler.canScrollVertically(0)
            }
        }
    }
    private fun providerInitBinding() {
        with(binding) {
            providerRecycler.apply {
                watchProviderAdapter.setProvidersList(ArrayList(providerList))
                adapter = watchProviderAdapter
//                providerRecycler.canScrollVertically(0)
            }
        }
    }

    private fun ActorObserver() {
        actorViewModel.actorList.observe(viewLifecycleOwner) { actorList ->
            this.actorList = actorList?.cast
        }
    }
    private fun providerObserver() {
        watchProvideViewModel.providerList.observe(viewLifecycleOwner) { providerList ->
            this.providerList = providerList?.results?.TR?.flatrate
        }
    }



}






