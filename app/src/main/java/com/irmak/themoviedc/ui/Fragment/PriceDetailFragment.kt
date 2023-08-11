package com.irmak.themoviedc.ui.Fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.irmak.themoviedc.adapter.WatchProviderAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.databinding.FragmentPriceDetailBinding
import com.irmak.themoviedc.model.popularModel.PopularMovieDetailResponse
import com.irmak.themoviedc.model.tvDetailModel.TvDetailModel
import com.irmak.themoviedc.model.watchProviders.MovieProviderDetails
import com.irmak.themoviedc.model.watchProviders.Offer
import com.irmak.themoviedc.model.watchProviders.ProviderPriceResponse
import com.irmak.themoviedc.model.watchProviders.TvMovieProviderDetails
import com.irmak.themoviedc.repository.PopularMovieRepository
import com.irmak.themoviedc.repository.ProviderPriceRepository
import com.irmak.themoviedc.repository.TvDetailRepository
import com.irmak.themoviedc.repository.TvWatchProviderRepository
import com.irmak.themoviedc.repository.WatchProviderRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.ui.extensions.loadImage
import com.irmak.themoviedc.ui.extensions.observChoice
import com.irmak.themoviedc.viewModel.ViewModelSub.PopularMovieViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.ProviderPriceViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.TvDetailViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.TvWatchProvideViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.WatchProvideViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.PopularMovieViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.ProviderPriceViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.TvDetailViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.TvWatchProvideViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.WatchProvideViewModelFactory
import retrofit2.Retrofit
import kotlin.properties.Delegates

var providersId: Int? = 1
var layoutCheck: Int = 0

class PriceDetailFragment : Fragment() {
    lateinit var binding: FragmentPriceDetailBinding
    var link: String? = null
    var linkBR: String? = null
    var links: MutableList<String?> = mutableListOf()
    var linksBR: MutableList<String?> = mutableListOf()
//    var providerPriceList: List<Offer> by Delegates.observable(arrayListOf()) { _, _, newValue ->
//        if (newValue.isNullOrEmpty().not()) {
//        }
//        Log.e("priceList", "providerPriceList -> ${newValue}")
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPriceDetailBinding.inflate(inflater, container, false)
        layout()
        return binding.root
    }

    private fun layout() {
        if (layoutCheck == 1) {
            binding.buyAndRentLayout.visibility = View.VISIBLE
        } else if (layoutCheck == 2) {
            binding.flatLayout.visibility = View.VISIBLE
        }
    }

    private val retrofit: Retrofit by lazy {
        RetrofitClient.getRetrofit()
    }
    private val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
    private val providerPriceRepository: ProviderPriceRepository by lazy {
        ProviderPriceRepository(movieApi)
    }
    private val providerPriceViewModel: ProviderPriceViewModel by viewModels {
        ProviderPriceViewModelFactory(providerPriceRepository)
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
    private val popularMovieRepository: PopularMovieRepository by lazy {
        PopularMovieRepository(movieApi)
    }
    private val popularMovieViewModel: PopularMovieViewModel by viewModels {
        PopularMovieViewModelFactory(popularMovieRepository)
    }
    private val tvWatchProviderRepository: TvWatchProviderRepository by lazy {
        TvWatchProviderRepository(movieApi)
    }
    private val tvWatchProvideViewModel: TvWatchProvideViewModel by viewModels {
        TvWatchProvideViewModelFactory(tvWatchProviderRepository)
    }
    private val tvDetailRepository: TvDetailRepository by lazy {
        TvDetailRepository(movieApi)
    }
    private val tvDetailViewModel: TvDetailViewModel by viewModels {
        TvDetailViewModelFactory(tvDetailRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        providerPriceViewModel.getTvProvidersPrice()
        tvDetailViewModel.getTvDetail()
        tvWatchProvideViewModel.getTvProviders()
        watchProvideViewModel.getProviders()
        popularMovieViewModel.getPopularMovieDetail()

        providerPriceViewModel.providePriceList.observe(
            viewLifecycleOwner,
            ::providerObserver
        )

        watchProvideViewModel.providerList.observe(
            viewLifecycleOwner,
            ::watchProviderObserver
        )
        watchProvideViewModel.providerList.observe(
            viewLifecycleOwner,
            ::watchProviderFlatrateObserver
        )
        tvWatchProvideViewModel.tvProviderList.observe(
            viewLifecycleOwner,
            ::tvProviderFlatrateObserver
        )
        if (observChoice == 1) {
            popularMovieViewModel.popularMovieDetailList.observe(
                viewLifecycleOwner,
                ::movieDetailObserver
            )
            providerPriceViewModel.providePriceList.observe(
                viewLifecycleOwner,
                ::providerMovieObserver
            )
        } else if (observChoice == 2) {
            tvDetailViewModel.tvDetailList.observe(
                viewLifecycleOwner,
                ::tvDetailObserver
            )

        }

        clickLink()
        clickLinkBR()

    }

    private fun clickLink() {
        binding.flatLinkButon.setOnClickListener {
            val url = "$link}"
            if (link.isNullOrEmpty().not()) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                requireContext().startActivity(intent)
                links.clear()
            } else {
                Toast.makeText(requireContext(), "Website bulunamadı", Toast.LENGTH_SHORT).show()
            }
            Log.e("link", "$link:")
        }
    }

    private fun clickLinkBR() {
        binding.buyAndRateImageView.setOnClickListener {
            val url = "${linkBR}"
            if (linkBR.isNullOrEmpty().not()) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                requireContext().startActivity(intent)
                linksBR.clear()
            } else {
                Toast.makeText(requireContext(), "Website bulunamadı", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun watchProviderObserver(response: MovieProviderDetails) {
        response.results?.TR?.buy?.forEach { offer ->
            if (providersId == offer.provider_id) {
                binding.ProviderImage.loadImage("https://image.tmdb.org/t/p/original/${offer.logo_path}")

            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun watchProviderFlatrateObserver(response: MovieProviderDetails) {
        response.results?.TR?.flatrate?.forEach { offer ->
            if (providersId == offer.provider_id) {
                binding.ProviderFlatrateImage.loadImage("https://image.tmdb.org/t/p/original/${offer.logo_path}")
                binding.infoFlatText.text =
                    "Abonelik işlemleri için ${offer.provider_name}.com sayfasına gitmek ister misiniz?"
            }
        }
    }

    private fun tvProviderFlatrateObserver(response: TvMovieProviderDetails) {
        response.results.TR?.flatrate?.forEach { offer ->
            if (providersId == offer.provider_id) {
                binding.ProviderFlatrateImage.loadImage("https://image.tmdb.org/t/p/original/${offer.logo_path}")
                binding.infoFlatText.text =
                    "Abonelik işlemleri için ${offer.provider_name}.com sayfasına gitmek ister misiniz?"
            }
        }
    }

    private fun movieDetailObserver(response: PopularMovieDetailResponse?) {
        binding.flatrateBackImage.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response?.poster_path}")
        binding.buyAndRateImageView.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response?.poster_path}")
//        binding.buyAndRateImageView.loadImage("https://image.tmdb.org/t/p/w300_and_h450_bestv2${response?.backdrop_path}")
    }

    private fun tvDetailObserver(response: TvDetailModel?) {
        binding.flatrateBackImage.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response?.poster_path}")
//        binding.buyAndRateImageView.loadImage("https://image.tmdb.org/t/p/w300_and_h450_bestv2${response?.backdrop_path}")
    }

    @SuppressLint("SetTextI18n")
    private fun providerObserver(response: ProviderPriceResponse?) {

        response?.offers?.forEach { offer ->
// response.offers içindeki tüm verileri döngü ile bkıyoruz
            if (link.isNullOrEmpty().not()){
                return
            }
            for (offer in response.offers) {
                if (offer.monetization_type == "flatrate" && offer.provider_id == providersId) {
                    links.add(offer.urls.raw_web)
                }
            }
            link = if (links.isNotEmpty()) links[0] else ""


// response.offers içindeki tüm verileri döngü ile bkıyoruz
            for (offer in response.offers) {
                if (offer.monetization_type == "buy" || offer.monetization_type == "rent" && offer.provider_id == providersId) {
                    linksBR.add(offer.urls.raw_web)
                }
            }
            linkBR = if (linksBR.isNotEmpty()) linksBR[0] else ""

        }
    }

    @SuppressLint("SetTextI18n")
    private fun providerMovieObserver(response: ProviderPriceResponse?) {
        if (response?.offers.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "Sağlayıcı bulunamadı", Toast.LENGTH_SHORT).show()
//            val action = PriceDetailFragmentDirections.actionPriceDetailFragmentToDetailFragment()
//            findNavController().navigate(action)
        } else {

            response?.offers?.forEach { offer ->
                if (offer.monetization_type == "buy" && offer.presentation_type == "sd" && offer.provider_id == providersId) {
                    binding.buySdPrice.text = "SD: ${offer.retail_price.toString()} ₺"

                }
                if (offer.monetization_type == "buy" && offer.presentation_type == "hd" && offer.provider_id == providersId) {
                    binding.buyHdPrice.text = "HD: ${offer.retail_price.toString()} ₺"
                }
                if (offer.monetization_type == "buy" && offer.presentation_type == "4k" && offer.provider_id == providersId) {
                    binding.buy4kPrice.text = "4K: ${offer.retail_price.toString()} ₺"
                }
                if (offer.monetization_type == "rent" && offer.presentation_type == "sd" && offer.provider_id == providersId) {
                    binding.rentSdPrice.text = "SD: ${offer.retail_price.toString()} ₺"
                }
                if (offer.monetization_type == "rent" && offer.presentation_type == "hd" && offer.provider_id == providersId) {
                    binding.rentHdPrice.text = "HD: ${offer.retail_price.toString()} ₺"
                }
                if (offer.monetization_type == "rent" && offer.presentation_type == "4k" && offer.provider_id == providersId) {
                    binding.rent4kPrice.text = "4K: ${offer.retail_price.toString()} ₺ "
                }

            }
        }

    }
}