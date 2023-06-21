package com.irmak.themoviedc.ui.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.irmak.themoviedc.MainActivity
import com.irmak.themoviedc.adapter.SearchAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.data.remote.api.searchWord
import com.irmak.themoviedc.databinding.FragmentSearchBinding
import com.irmak.themoviedc.model.search.SearchResponse
import com.irmak.themoviedc.repository.SearchRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.viewModel.SearchViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.SearchViewModelFactory
import retrofit2.Retrofit
import kotlin.properties.Delegates


class SearchFragment : Fragment() {
    lateinit var binding: FragmentSearchBinding
    var searchList: List<SearchResponse>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            searchAdapter.setSearchLst(ArrayList(newValue))
        }
        Log.e("Delegates", "user -> ${newValue}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater,container,false)
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
                searchViewModel.getSearch()
                binding.searchRecycler.smoothScrollToPosition(0)
                return true
            }
        })
        return binding.root
    }

    private val searchAdapter:SearchAdapter by lazy {
        SearchAdapter()
    }
    private val retrofit: Retrofit by lazy {
        RetrofitClient.getRetrofit()
    }
    private val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
    private val searchRepository:SearchRepository by lazy {
        SearchRepository(movieApi)
    }
    private val searchViewModel:SearchViewModel by viewModels {
        SearchViewModelFactory(searchRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.getSearch()
        initRecyclerBind()
        searchObservable()
        retrofit
    }
    private fun initRecyclerBind(){
        with(binding){
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



}