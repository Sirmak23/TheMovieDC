package com.irmak.themoviedc.ui.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.irmak.themoviedc.MainActivity
import com.irmak.themoviedc.adapter.TvACtorAdapter
import com.irmak.themoviedc.adapter.TvDetailAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.databinding.FragmentTvDetailBinding
import com.irmak.themoviedc.model.trailer.TrailerResponse
import com.irmak.themoviedc.model.trailer.TvTrailerResponse
import com.irmak.themoviedc.model.tvActorModel.CastResponse
import com.irmak.themoviedc.model.tvDetailModel.TvDetailModel
import com.irmak.themoviedc.repository.TrailerRepository
import com.irmak.themoviedc.repository.TvActorRepository
import com.irmak.themoviedc.repository.TvDetailRepository
import com.irmak.themoviedc.repository.TvTrailerRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.ui.extensions.loadImage
import com.irmak.themoviedc.viewModel.TrailerViewModel
import com.irmak.themoviedc.viewModel.TvActorViewModel
import com.irmak.themoviedc.viewModel.TvDetailViewModel
import com.irmak.themoviedc.viewModel.TvTrailerViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.TrailerViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.TvActorViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.TvDetailViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.TvTrailerViewModelFactory
import retrofit2.Retrofit
import kotlin.properties.Delegates


class TvDetailFragment : Fragment() {
    private lateinit var binding: FragmentTvDetailBinding

    //    var movieList: List<TvDetailModel>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
//        if (newValue.isNullOrEmpty().not()) {
//            tvDetailAdapter.setTvList(ArrayList(newValue))
//        }
//        Log.e("Delegates", "user -> ${newValue}")
//    }
    var tvActorList: List<CastResponse>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            tvActorAdapter.setTvActorLst(ArrayList(newValue))
        }
        Log.e("Delegates", "user -> ${newValue}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvDetailBinding.inflate(inflater, container, false)
        tvDetailViewModel.getTvDetail()
        tvDetailViewModel.tvDetailList.observe(
            viewLifecycleOwner,
            ::tvDetailObserver
        )

        ChoiceVideo = "tv"
        return binding.root
    }

    private val tvTrailerRepository: TvTrailerRepository by lazy {
        TvTrailerRepository(movieApi)
    }
    private val tvTrailerViewModel: TvTrailerViewModel by viewModels {
        TvTrailerViewModelFactory(tvTrailerRepository)
    }

    private val tvDetailAdapter: TvDetailAdapter by lazy {
        TvDetailAdapter()
    }
    private val retrofit: Retrofit by lazy {
        RetrofitClient.getRetrofit()
    }
    private val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
    private val tvDetailRepository: TvDetailRepository by lazy {
        TvDetailRepository(movieApi)
    }
    private val tvDetailViewModel: TvDetailViewModel by viewModels {
        TvDetailViewModelFactory(tvDetailRepository)
    }

    private val trailerRepository: TrailerRepository by lazy {
        TrailerRepository(movieApi)
    }
    private val trailerViewModel: TrailerViewModel by viewModels {
        TrailerViewModelFactory(trailerRepository)
    }

    private val tvActorAdapter: TvACtorAdapter by lazy {
        TvACtorAdapter()
    }
    private val tvActorRepository: TvActorRepository by lazy {
        TvActorRepository(movieApi)
    }
    private val TvActorViewModel: TvActorViewModel by viewModels {
        TvActorViewModelFactory(tvActorRepository)
    }

    var tvVideo: String = "null"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val main = activity as MainActivity
        main.setBottomNavigationViewVisibility(false)
        tvDetailViewModel.getTvDetail()
        tvTrailerViewModel.getTvVideo()
        TvActorViewModel.getTvActorDetail()
        actorInitBinding()
        ActorObserver()
        tvTrailerViewModel.tvTrailerList.observe(viewLifecycleOwner, ::tvTrailerObserve)
        binding.TvBackButtonImageView.setOnClickListener {
            findNavController().navigate(TvDetailFragmentDirections.actionTvDetailFragmentToTvPopularFragment())
        }
        binding.TvTrailerButton.setOnClickListener {
            if (tvVideo == "null") {
                Toast.makeText(requireContext(), "Film fragmanına erişilemiyor", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val action = TvDetailFragmentDirections.actionTvDetailFragmentToVideoPlayerFragment()
                findNavController().navigate(action)
            }
        }
    }
    fun tvTrailerObserve(resp: TvTrailerResponse?) {
//         video = resp?.results?.get(0)?.let { it.key.toString() } ?:"null"
        if (resp != null && resp.results != null && resp.results.isNotEmpty()) {
            tvVideo = resp.results.get(0).key?.toString() ?: ""

        } else {

        }
    }

    @SuppressLint("SetTextI18n")
    private fun tvDetailObserver(response: TvDetailModel?) {
        binding.TvDetailNameTextView.text = response?.name
        binding.imdbDetailTextView.text = "${response?.vote_average}/10"
        binding.DetailYt.text = "Y.T: ${response?.first_air_date}"
        binding.TvDetailOzetTExt.text = response?.overview
        binding.tvDetailImageView.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response?.poster_path}")
        binding.tvPopularDetailBack.loadImage("https://image.tmdb.org/t/p/w300_and_h450_bestv2${response?.backdrop_path}")
//        binding.popularDetailBack.loadImage("https://www.themoviedb.org/t/p/w533_and_h300_bestv2${response?.backdrop_path}")
        binding.imdbDetailPhoto2.loadImage("https://ia.media-imdb.com/images/M/MV5BODc4MTA3NjkzNl5BMl5BcG5nXkFtZTgwMDg0MzQ2OTE@._V1_.png")
    }

    private fun actorInitBinding() {
        with(binding) {
            TvActorRecyler.apply {
                tvActorAdapter.setTvActorLst(ArrayList(tvActorList))
                adapter = tvActorAdapter
                TvActorRecyler.canScrollVertically(0)
            }
        }
    }
    private fun ActorObserver() {
        TvActorViewModel.tvActorList.observe(viewLifecycleOwner) { tvActorList ->
            this.tvActorList = tvActorList?.cast
        }
    }
}
