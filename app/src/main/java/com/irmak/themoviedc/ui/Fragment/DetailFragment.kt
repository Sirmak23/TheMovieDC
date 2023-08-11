package com.irmak.themoviedc.ui.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.view.isNotEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.irmak.themoviedc.MainActivity
import com.irmak.themoviedc.R
import com.irmak.themoviedc.adapter.ActorAdapter
import com.irmak.themoviedc.adapter.PopularMovieDetailAdapter
import com.irmak.themoviedc.adapter.ProvideFlatrateAdapter
import com.irmak.themoviedc.adapter.WatchProviderAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.data.remote.api.MovieOrTvID
import com.irmak.themoviedc.data.remote.api.objectType
import com.irmak.themoviedc.databinding.FragmentDetailBinding
import com.irmak.themoviedc.model.actorModel.ActorDetail
import com.irmak.themoviedc.model.actorModel.ActorMovies
import com.irmak.themoviedc.model.actorModel.HisMovie
import com.irmak.themoviedc.model.popularModel.PopularMovieDetailResponse
import com.irmak.themoviedc.model.tvPopularModel.TvPopularResponse
import com.irmak.themoviedc.model.watchProviders.ProviderPriceResponse
import com.irmak.themoviedc.repository.ActorRepository
import com.irmak.themoviedc.repository.PopularMovieRepository
import com.irmak.themoviedc.repository.TrailerRepository
import com.irmak.themoviedc.repository.WatchProviderRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.ui.extensions.loadImage
import com.irmak.themoviedc.ui.extensions.observChoice
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
    var actorList: List<ActorDetail>? by Delegates.observable(
        arrayListOf()
    ) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            actorAdapter.setActorList(ArrayList(newValue))
        }
        Log.e("Delegates", "user -> ${newValue}")
    }
    var providerList: List<com.irmak.themoviedc.model.watchProviders.Provider>? by Delegates.observable(
        arrayListOf()
    ) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            watchProviderAdapter.setProvidersList(ArrayList(newValue))
        }
        Log.e("Delegates", "providerList -> ${newValue}")
    }
    var providerFlatrateList: List<com.irmak.themoviedc.model.watchProviders.Provider>? by Delegates.observable(
        arrayListOf()
    ) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            ProviderFlatrateAdapter.setProvidersList(ArrayList(newValue))
        }
        Log.e("Delegates", "providerFlatrateList -> ${newValue}")
    }

    private lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        popularMovieViewModel.getPopularMovieDetail()
        popularMovieViewModel.popularMovieDetailList.observe(
            viewLifecycleOwner,
            ::movieDetailObserver
        )
        watchProvideViewModel.getProviders()
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_down)
        binding.popularDetailBack.startAnimation(animation)
        objectType = "movie"
        ChoiceVideo = "movie"
        observChoice = 1
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
    private val retrofitJW: Retrofit by lazy {
        RetrofitClient.getRetrofitJW()
    }
    private val movieApiJw: MovieApi by lazy {
        retrofitJW.create(MovieApi::class.java)
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
    private val watchProviderRepository: WatchProviderRepository by lazy {
        WatchProviderRepository(movieApi)
    }
    private val watchProvideViewModel: WatchProvideViewModel by viewModels {
        WatchProvideViewModelFactory(watchProviderRepository)
    }
    private val watchProviderAdapter: WatchProviderAdapter by lazy {
        WatchProviderAdapter()
    }
    private val ProviderFlatrateAdapter: ProvideFlatrateAdapter by lazy {
        ProvideFlatrateAdapter()
    }
    var video: String = "null"

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val main = activity as MainActivity
        main.setBottomNavigationViewVisibility(false)
        actorViewModel.getActorDetail()
        providerInitBinding()
        providerFletrateInitBinding()
        actorInitBinding()
        providerObserver()
        visibilityObserve()
        visibilityFlatObserve()
        providerFlatrateObserver()
        ActorObserver()
        trailerViewModel.getVideo()
        setOnCLicks()
        trailerViewModel.trailerList.observe(viewLifecycleOwner, ::trailerObserve)
        //        providerPriceViewModel.providePriceList.observe(viewLifecycleOwner, ::priceObserver)

    }

    private fun visibilityObserve(){
        watchProvideViewModel.providerList.observe(viewLifecycleOwner) { providerList ->
            if (providerList.results?.TR?.buy.isNullOrEmpty()) {
                binding.buyAndRentText.visibility = View.GONE
                binding.providerRecycler.visibility = View.GONE
            }
        }
    }
    private fun visibilityFlatObserve(){
        watchProvideViewModel.providerList.observe(viewLifecycleOwner) { providerList ->
            if (providerList.results?.TR?.flatrate.isNullOrEmpty()){
            binding.providerFlatrateText.visibility = View.GONE
            binding.providerFlatrateRecycler.visibility = View.GONE
        }
        }
    }

    private fun priceObserver(response: ProviderPriceResponse?) {
//        response?.offers?.forEach { offer ->
//            providerList?.firstOrNull(){ it.provider_id == offer.provider_id }?.retail_price = offer.retail_price
//            providerList?.indexOfFirst { it.provider_id == offer.provider_id }?.let { watchProviderAdapter.notifyItemChanged(it) }

//        providerList?.firstOrNull()?.retail_price = response?.offers?.get(0)?.retail_price
//        providerList?.drop(1)?.firstOrNull()?.retail_price = response?.offers?.get(1)?.retail_price
//        watchProviderAdapter.notifyItemChanged(0)
//        watchProviderAdapter.notifyItemChanged(1)
    }

private fun setOnCLicks(){
    binding.backButtonImageView.setOnClickListener {
        findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToPopularFragment())
    }
    binding.trailerButton.setOnClickListener {
        if (video == "null") {
            Toast.makeText(requireContext(), "Film fragmanına erişilemedi!", Toast.LENGTH_SHORT)
                .show()
        } else {
            val action = DetailFragmentDirections.actionDetailFragmentToVideoPlayerFragment()
            findNavController().navigate(action)
        }
    }
}
    fun trailerObserve(resp: com.irmak.themoviedc.model.trailer.TrailerResponse?) {
//         video = resp?.results?.get(0)?.let { it.key.toString() } ?:"null"
        if (resp != null && resp.results != null && resp.results.isNotEmpty()) {
            video = resp.results.get(0).key ?: ""

        }
    }

    @SuppressLint("SetTextI18n")
    private fun movieDetailObserver(response: PopularMovieDetailResponse?){
        binding.MovieDetailNameTextView.text = response?.title
        binding.imdbDetailTextView.text = "${response?.vote_average}/10"
        binding.DetailYt.text = "Y.T: ${response?.release_date}"
        binding.DetailOzetTExt.text = response?.overview
        MovieOrTvID = response?.id!!
        binding.movieDEtailImageView.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2"+ response.poster_path)
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
                if (providerList.isNullOrEmpty()){
                    watchProviderAdapter.setProvidersList(ArrayList())
                } else{
                    watchProviderAdapter.setProvidersList(ArrayList(providerList))
                }
                adapter = watchProviderAdapter
            }
        }
    }

    private fun providerFletrateInitBinding() {
        with(binding) {
            providerFlatrateRecycler.apply {
                if (providerFlatrateList.isNullOrEmpty()) {
                    ProviderFlatrateAdapter.setProvidersList(ArrayList())
                } else {
                    ProviderFlatrateAdapter.setProvidersList(ArrayList(providerFlatrateList))
                }
                adapter = ProviderFlatrateAdapter
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
            this.providerList = providerList?.results?.TR?.buy
        }
    }

    private fun providerFlatrateObserver() {
        watchProvideViewModel.providerList.observe(viewLifecycleOwner) { providerFlatrateList ->
            this.providerFlatrateList = providerFlatrateList?.results?.TR?.flatrate
        }
    }


}



