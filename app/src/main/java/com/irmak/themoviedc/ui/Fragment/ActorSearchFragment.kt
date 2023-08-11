package com.irmak.themoviedc.ui.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.MainActivity
import com.irmak.themoviedc.R
import com.irmak.themoviedc.adapter.ActorSearchAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.data.remote.api.actorSearchWord
import com.irmak.themoviedc.data.remote.api.searchWord
import com.irmak.themoviedc.databinding.FragmentActorSearchBinding
import com.irmak.themoviedc.databinding.FragmentSearchBinding
import com.irmak.themoviedc.model.search.Person
import com.irmak.themoviedc.repository.ActorSearchRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.viewModel.ViewModelSub.ActorSearchViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.ActorSearchViewModelFactory
import retrofit2.Retrofit
import kotlin.properties.Delegates

class ActorSearchFragment : Fragment() {
    lateinit var binding:FragmentActorSearchBinding
    var actorMovieList: List<Person>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            actorSearchAdapter.setActorMovie(ArrayList(newValue))
        }
        Log.e("actorMovieList: ", "$actorMovieList -> ${newValue}")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActorSearchBinding.inflate(inflater,container,false)
        val main = activity as MainActivity
        main.setBottomNavigationViewVisibility(true)

        binding.searchViewActor.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                actorSearchWord = newText
                actorSearchViewModel.getActorDetail()
                binding.actorSearchRecyclerview.smoothScrollToPosition(0)
                return true
            }
        })
        binding.searchBarActor.setOnClickListener {
            binding.searchViewActor.isIconified = false
            binding.searchViewActor.requestFocusFromTouch()
        }
        return binding.root
    }


    private val retrofit: Retrofit by lazy {
        RetrofitClient.getRetrofit()
    }
    private val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
    private val actorSearchAdapter:ActorSearchAdapter by lazy {
        ActorSearchAdapter()
    }
    private val actorSearchRepository:ActorSearchRepository by lazy {
        ActorSearchRepository(movieApi)
    }
    private val actorSearchViewModel:ActorSearchViewModel by viewModels {
        ActorSearchViewModelFactory(actorSearchRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.actorSearchRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    val imm =
                        requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }
        })
        initActorSearch()
        actorSearchObservable()

    }

    fun initActorSearch(){
        with(binding) {
            actorSearchRecyclerview.apply {
                actorSearchAdapter.setActorMovie(ArrayList(actorMovieList))
                adapter = actorSearchAdapter
            }
        }
    }
    fun actorSearchObservable(){
        actorSearchViewModel.actorSearchList.observe(viewLifecycleOwner){actorMovieList->
            this.actorMovieList = actorMovieList?.results
        }
    }


}