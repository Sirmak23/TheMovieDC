package com.irmak.themoviedc.ui.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.service.autofill.Validators.and
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat.animate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.irmak.themoviedc.MainActivity
import com.irmak.themoviedc.R
import com.irmak.themoviedc.adapter.ActorAdapter
import com.irmak.themoviedc.adapter.PopularMovieDetailAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.databinding.FragmentDetailBinding
import com.irmak.themoviedc.model.actorModel.ActorDetail
import com.irmak.themoviedc.model.popularModel.PopularMovieDetailResponse
import com.irmak.themoviedc.model.trailer.TrailerResponse
import com.irmak.themoviedc.repository.ActorRepository
import com.irmak.themoviedc.repository.PopularMovieRepository
import com.irmak.themoviedc.repository.TrailerRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.ui.extensions.loadImage
import com.irmak.themoviedc.viewModel.ActorViewModel
import com.irmak.themoviedc.viewModel.PopularMovieViewModel
import com.irmak.themoviedc.viewModel.TrailerViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.ActorViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.PopularMovieViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.TrailerViewModelFactory
import retrofit2.Retrofit
import kotlin.properties.Delegates


class DetailFragment : Fragment() {
    var actorList: List<ActorDetail>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            actorAdapter.setActorList(ArrayList(newValue))
        }
        Log.e("Delegates", "user -> ${newValue}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    var video: String = "null"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val main = activity as MainActivity
        main.setBottomNavigationViewVisibility(false)
        actorViewModel.getActorDetail()
        actorInitBinding()
        ActorObserver()
        trailerViewModel.getVideo()
        binding.backButtonImageView.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToPopularFragment())
        }
        binding.homePageButton.setOnClickListener {
            if (video == "null") {
                Toast.makeText(requireContext(), "Film fragmanına erişilemiyor", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val url = "https://www.youtube.com/watch?v=${video}"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                view.context.startActivity(intent)
            }
        }
//        popularMovieViewModel.popularMovieDetailList.observe(
//            viewLifecycleOwner,
//            ::movieDetailObserver
//        )
//        popularMovieViewModel.getPopularMovieDetail()
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
        binding.popularDetailBack.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response?.backdrop_path}")
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

    private fun ActorObserver() {
        actorViewModel.actorList.observe(viewLifecycleOwner) { actorList ->
            this.actorList = actorList?.cast
        }
    }


}






