package com.irmak.themoviedc.ui.Fragment

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.irmak.themoviedc.R
import com.irmak.themoviedc.adapter.RecommendationAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.databinding.FragmentRecommendationBinding
import com.irmak.themoviedc.model.recommendationModel.RecomTvShow
import com.irmak.themoviedc.repository.RecommendationRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.viewModel.RecommendationViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.RecommendationViewModelFactory
import retrofit2.Retrofit
import kotlin.math.abs
import kotlin.properties.Delegates

class RecommendationFragment : DialogFragment() {

    var recomList: List<RecomTvShow>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            recommendationAdapter.setRecommeList(ArrayList(newValue))
        }
        Log.e("Delegates", "user -> ${newValue}")
    }
  private  lateinit var binding:FragmentRecommendationBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRecommendationBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onStart() {
        super.onStart()
        // DialogFragment'in boyut ve konum ayarlrı
        dialog?.window?.apply {
            setGravity(Gravity.CENTER)
            setStyle(STYLE_NORMAL, R.style.CustomDialogTheme)
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }
    private val recommendationAdapter: RecommendationAdapter by lazy {
        RecommendationAdapter()
    }
    private val retrofit: Retrofit by lazy {
        RetrofitClient.getRetrofit()
    }
    private val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
    private val recommendationRepository: RecommendationRepository by lazy {
        RecommendationRepository(movieApi)
    }
    private val recommendationViewModel: RecommendationViewModel by viewModels {
        RecommendationViewModelFactory(recommendationRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recommendationViewModel.getRecommendations()
        initViewPager()
        tvPopularObserve()
        setupTransformer()
    }
    private fun initViewPager() {
        with(binding) {
            viewPagerRecom.apply {
                recommendationAdapter.setRecommeList(ArrayList(recomList))
                adapter = recommendationAdapter
                offscreenPageLimit = 3
                clipToPadding = false
                clipChildren = false
                getChildAt(0).overScrollMode = androidx.recyclerview.widget.RecyclerView.OVER_SCROLL_NEVER
            }
        }
    }
    private fun tvPopularObserve() {
        recommendationViewModel.recomList.observe(viewLifecycleOwner) { recomList ->
            this.recomList = recomList?.results
        }
    }
    private fun setupTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.75f + r * 0.14f
        }
        binding.viewPagerRecom.setPageTransformer(transformer)
    }
    companion object {
        fun newInstance(): RecommendationFragment {
            return RecommendationFragment()
        }
    }
}

