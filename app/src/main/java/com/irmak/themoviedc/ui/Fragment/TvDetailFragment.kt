package com.irmak.themoviedc.ui.Fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.irmak.themoviedc.MainActivity
import com.irmak.themoviedc.adapter.SeasonInfoAdapter
import com.irmak.themoviedc.adapter.TvACtorAdapter
import com.irmak.themoviedc.adapter.TvDetailAdapter
import com.irmak.themoviedc.adapter.TvWatchProviderAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.data.remote.api.MovieOrTvID
import com.irmak.themoviedc.data.remote.api.objectType
import com.irmak.themoviedc.data.remote.api.seasonNo
import com.irmak.themoviedc.databinding.FragmentTvDetailBinding
import com.irmak.themoviedc.model.seasonInfoModel.EpisodeData
import com.irmak.themoviedc.model.tvActorModel.CastResponse
import com.irmak.themoviedc.model.tvDetailModel.TvDetailModel
import com.irmak.themoviedc.model.watchProviders.TvProvider
import com.irmak.themoviedc.repository.*
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.ui.extensions.loadImage
import com.irmak.themoviedc.ui.extensions.observChoice
import com.irmak.themoviedc.viewModel.*
import com.irmak.themoviedc.viewModel.ViewModelSub.ProviderPriceViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.SeasonInfoViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.TrailerViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.TvActorViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.TvDetailViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.TvTrailerViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.TvWatchProvideViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.*
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
    var seasonInfoList: List<EpisodeData>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            seasonInfoAdapter.setSeasonList(ArrayList(newValue))
        }
        Log.e("Delegates", "user -> ${newValue}")
    }
    var tvProviderList: List<TvProvider>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            tvWatchProviderAdapter.setTvProvidersList(ArrayList(newValue))
        }
        Log.e("Delegates", "tvProviderList -> ${newValue}")
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
        objectType = "show"
        observChoice = 2
        binding.TvRecomButtonImageView.setOnClickListener {
            val dialogFragment = RecommendationFragment.newInstance()
            dialogFragment.show(childFragmentManager, "RecommendationFragment")
//            Handler().postDelayed({
//                val dialogFragment = RecommendationFragment.newInstance()
//                dialogFragment.show(childFragmentManager, "RecommendationFragment")
//            }, 5000)
        }
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
    private val retrofitJW:Retrofit by lazy {
        RetrofitClient.getRetrofitJW()
    }
    private val movieApiJw:MovieApi by lazy {
        retrofitJW.create(MovieApi::class.java)
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
    private val seasonInfoAdapter: SeasonInfoAdapter by lazy {
        SeasonInfoAdapter()
    }
    private val seasonInfoRepository: SeasonInfoRepository by lazy {
        SeasonInfoRepository(movieApi)
    }
    private val seasonInfoViewModel: SeasonInfoViewModel by viewModels {
        SeasonInfoViewModelFactory(seasonInfoRepository)
    }
    private val tvWatchProviderRepository: TvWatchProviderRepository by lazy {
        TvWatchProviderRepository(movieApi)
    }
    private val tvWatchProvideViewModel: TvWatchProvideViewModel by viewModels {
        TvWatchProvideViewModelFactory(tvWatchProviderRepository)
    }
    private val tvWatchProviderAdapter: TvWatchProviderAdapter by lazy {
        TvWatchProviderAdapter()
    }
    private val providerPriceRepository:ProviderPriceRepository by lazy {
        ProviderPriceRepository(movieApiJw)
    }
    private val providerPriceViewModel:ProviderPriceViewModel by viewModels {
        ProviderPriceViewModelFactory(providerPriceRepository)
    }

    var tvVideo: String = "null"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val main = activity as MainActivity
        main.setBottomNavigationViewVisibility(false)
        tvTrailerViewModel.getTvVideo()
        TvActorViewModel.getTvActorDetail()
        seasonInfoViewModel.getSeasonDetail()
        tvWatchProvideViewModel.getTvProviders()
//        providerPriceViewModel.getTvProvidersPrice()
        actorInitBinding()
        seasonInfoInıtBinding()
        ActorObserver()
        SeasonInfoObserver()
        providerInitBinding()
        providerObserver()
        tvTrailerViewModel.tvTrailerList.observe(viewLifecycleOwner, ::tvTrailerObserve)
        tvWatchProvideViewModel.tvProviderList.observe(viewLifecycleOwner, ::tvProvideText)
//        providerPriceViewModel.providePriceList.observe(viewLifecycleOwner, ::tvPriceObserver)
        binding.TvBackButtonImageView.setOnClickListener {
            findNavController().navigate(TvDetailFragmentDirections.actionTvDetailFragmentToTvPopularFragment())
        }
        binding.TvTrailerButton.setOnClickListener {
            if (tvVideo == "null") {
                Toast.makeText(requireContext(), "Film fragmanına erişilemiyor", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val action =
                    TvDetailFragmentDirections.actionTvDetailFragmentToVideoPlayerFragment()
                findNavController().navigate(action)
            }
        }

    }

    private fun tvPriceObserver(response: com.irmak.themoviedc.model.watchProviders.ProviderPriceResponse?) {

    }

   private fun tvProvideText(response: com.irmak.themoviedc.model.watchProviders.TvMovieProviderDetails){
       if (response.results.TR?.flatrate?.get(0)?.logo_path.isNullOrEmpty()){
           binding.infoTextProvide.visibility = TextView.VISIBLE
       }else{
           binding.infoTextProvide.visibility = TextView.GONE
       }
   }


    private fun providerInitBinding() {
        with(binding) {
            providerRecycler.apply {
                if (tvProviderList.isNullOrEmpty()){
                    tvWatchProviderAdapter.setTvProvidersList(ArrayList(emptyList()))
                }else{
                    tvWatchProviderAdapter.setTvProvidersList(ArrayList(tvProviderList))
                }
                adapter = tvWatchProviderAdapter
//                providerRecycler.canScrollVertically(0)
            }
        }
    }

    private fun providerObserver() {
        tvWatchProvideViewModel.tvProviderList.observe(viewLifecycleOwner) { tvProviderList ->
            this.tvProviderList = tvProviderList?.results?.TR?.flatrate
        }
    }

    fun tvTrailerObserve(resp: com.irmak.themoviedc.model.trailer.TvTrailerResponse?) {
//         video = resp?.results?.get(0)?.let { it.key.toString() } ?:"null"
        if (resp != null && resp.results != null && resp.results.isNotEmpty()) {
            tvVideo = resp.results.get(0).key.toString()

        } else {

        }
    }

    @SuppressLint("SetTextI18n")
    private fun tvDetailObserver(response: TvDetailModel?) {
        MovieOrTvID = response?.id!!
        binding.TvDetailNameTextView.text = response?.name
        binding.imdbDetailTextView.text = "${response?.vote_average}/10"
        binding.DetailYt.text = "Y.T: ${response?.first_air_date}"
        binding.TvDetailOzetTExt.text = response?.overview
        binding.tvDetailImageView.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response?.poster_path}")
        binding.tvPopularDetailBack.loadImage("https://image.tmdb.org/t/p/w600_and_h900_bestv2${response?.backdrop_path}")
//        binding.tvPopularDetailBack.loadImage("https://image.tmdb.org/t/p/w300_and_h450_bestv2${response?.backdrop_path}")
//        binding.popularDetailBack.loadImage("https://www.themoviedb.org/t/p/w533_and_h300_bestv2${response?.backdrop_path}")
        binding.imdbDetailPhoto2.loadImage("https://ia.media-imdb.com/images/M/MV5BODc4MTA3NjkzNl5BMl5BcG5nXkFtZTgwMDg0MzQ2OTE@._V1_.png")
        binding.seasonChoiceText.setOnClickListener {
            val seasonCount = response?.number_of_seasons ?: 0
            val seasons = IntArray(seasonCount) { it + 1 }
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Görüntülemek istediğiniz sezonu seçin.")
            builder.setItems(
                seasons.map { it.toString() }.toTypedArray(),
                DialogInterface.OnClickListener { _, which ->
                    val selectedSeason = seasons[which]
                    seasonNo = selectedSeason
                    seasonInfoViewModel.getSeasonDetail()
                    binding.seasonInfoRecycler.smoothScrollToPosition(0)
                })
            builder.create().show()
        }
    }

    private fun actorInitBinding() {
        with(binding) {
            TvActorRecyler.apply {
                if (tvActorList.isNullOrEmpty()){
                    tvActorAdapter.setTvActorLst(ArrayList(emptyList()))
                }else{
                    tvActorAdapter.setTvActorLst(ArrayList(tvActorList))
                }
                adapter = tvActorAdapter
                TvActorRecyler.canScrollVertically(0)
            }
        }
    }

    private fun seasonInfoInıtBinding() {
        with(binding) {
            seasonInfoRecycler.apply {
                if (seasonInfoList.isNullOrEmpty()){
                    seasonInfoAdapter.setSeasonList(ArrayList(emptyList()))
                }else{
                    seasonInfoAdapter.setSeasonList(ArrayList(seasonInfoList))
                }
                adapter = seasonInfoAdapter
                seasonInfoRecycler.canScrollVertically(0)
            }
        }
    }

    private fun ActorObserver() {
        TvActorViewModel.tvActorList.observe(viewLifecycleOwner) { tvActorList ->
            this.tvActorList = tvActorList?.cast
        }
    }

    private fun SeasonInfoObserver() {
        seasonInfoViewModel.seasonInfoList.observe(viewLifecycleOwner) { seasonInfoList ->
            this.seasonInfoList = seasonInfoList?.episodes
        }
    }
}

