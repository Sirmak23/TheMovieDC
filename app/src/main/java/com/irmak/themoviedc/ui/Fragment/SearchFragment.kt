package com.irmak.themoviedc.ui.Fragment

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.preference.AndroidResources
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.MainActivity
import com.irmak.themoviedc.R
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
        binding.searchBar.setOnClickListener {
            binding.searchView.isIconified = false
            binding.searchView.requestFocusFromTouch()
        }
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
        binding.searchRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
                }

        })

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