package com.irmak.themoviedc.ui.Fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.MainActivity
import com.irmak.themoviedc.adapter.SearchAdapter
import com.irmak.themoviedc.adapter.TrendAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.data.remote.api.searchWord
import com.irmak.themoviedc.data.remote.api.timePeriod
import com.irmak.themoviedc.databinding.FragmentSearchBinding
import com.irmak.themoviedc.model.search.SearchResponse
import com.irmak.themoviedc.model.trendAll.TrendResponse
import com.irmak.themoviedc.repository.SearchRepository
import com.irmak.themoviedc.repository.TrendRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.viewModel.ViewModelSub.SearchViewModel
import com.irmak.themoviedc.viewModel.ViewModelSub.TrendViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.SearchViewModelFactory
import com.irmak.themoviedc.viewModel.viewModelFactory.TrendVieModelFactory
import retrofit2.Retrofit
import kotlin.properties.Delegates


class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    var trendVisibiliy: Boolean = true
    var searchList: List<SearchResponse>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            searchAdapter.setSearchLst(ArrayList(newValue))
        }
        Log.e("Delegates", "searchList -> ${newValue}")
    }
    var trendList: List<TrendResponse>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            trendAdapter.setTrendAllList(ArrayList(newValue))
        }
        Log.e("Delegates", "trendList -> ${newValue}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        val main = activity as MainActivity
        main.setBottomNavigationViewVisibility(true)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // Arama butonuna basıldığında gerçekleştirilecek işlemler
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Her metin değiştiğinde gerçekleştirilecek işlemler
                searchWord = newText
                binding.TrendText.visibility = TextView.GONE
                binding.TrendDay.visibility = TextView.GONE
                searchViewModel.getSearch()
                if (newText.isEmpty()) {
                    initRecyclerBindTrend()
                    binding.TrendText.visibility = TextView.VISIBLE
                    binding.TrendDay.visibility = TextView.VISIBLE

                } else {
                    initRecyclerBind()
                }
                binding.searchRecycler.smoothScrollToPosition(0)
                return true
            }
        })
        return binding.root
    }

    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter()
    }
    private val retrofit: Retrofit by lazy {
        RetrofitClient.getRetrofit()
    }
    private val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
    private val searchRepository: SearchRepository by lazy {
        SearchRepository(movieApi)
    }
    private val searchViewModel: SearchViewModel by viewModels {
        SearchViewModelFactory(searchRepository)
    }
    private val trendAdapter: TrendAdapter by lazy {
        TrendAdapter()
    }
    private val trendRepository: TrendRepository by lazy {
        TrendRepository(movieApi)
    }
    private val trendViewModel: TrendViewModel by viewModels {
        TrendVieModelFactory(trendRepository)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    val imm =
                        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }
        })
        binding.searchBar.setOnClickListener {
            initRecyclerBind()
            binding.TrendText.visibility = TextView.GONE
            binding.TrendDay.visibility = TextView.GONE
            binding.searchView.isIconified = false
            binding.searchView.requestFocusFromTouch()
        }
        trendViewModel.getTrendAll()
        searchViewModel.getSearch()
        initRecyclerBindTrend()
        searchObservable()
        setTrendPeriod()
        trendObservable()
    }
    private fun setTrendPeriod(){
        var clickCount = 0
        binding.TrendDay.setOnClickListener{
            clickCount++
            if (clickCount == 1){
                binding.TrendDay.text = "[ Hafta ↓]"
//                binding.TrendDay.setTextColor(Color.CYAN)
                timePeriod = "week"
                trendViewModel.getTrendAll()
            }else if (clickCount == 2){
                binding.TrendDay.text = "[ Bugün ↓]"
                timePeriod = "day"
                trendViewModel.getTrendAll()

                clickCount = 0
            }


        }
    }

    private fun initRecyclerBindTrend() {
        with(binding) {
            searchRecycler.apply {
                trendAdapter.setTrendAllList(ArrayList(trendList))
                adapter = trendAdapter
            }
        }
    }

    private fun initRecyclerBind() {
        with(binding) {
            searchRecycler.apply {
                searchAdapter.setSearchLst(ArrayList(searchList))
                adapter = searchAdapter
            }
        }
    }

    private fun searchObservable() {
        searchViewModel.searchList.observe(viewLifecycleOwner) { searchList ->
            Log.e("tag", "$searchList")
            this.searchList = searchList.results
        }
    }

    private fun trendObservable() {
        trendViewModel.trendList.observe(viewLifecycleOwner) { trendList ->
            Log.e("tag", "$trendList")
            this.trendList = trendList?.results
        }
    }


}