package com.irmak.themoviedc.ui.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.irmak.themoviedc.MainActivity
import com.irmak.themoviedc.R
import com.irmak.themoviedc.adapter.ActorMoviesAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.databinding.FragmentActorMovieBinding
import com.irmak.themoviedc.model.actorModel.HisMovie
import com.irmak.themoviedc.model.tvPopularModel.TvPopularResponse
import com.irmak.themoviedc.repository.ActorMovieRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.viewModel.ActorMovieViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.ActorMovieViewModelFactory
import retrofit2.Retrofit
import kotlin.properties.Delegates

class ActorMovieFragment : Fragment() {
    lateinit var binding:FragmentActorMovieBinding
    var actorMovieList: List<HisMovie>? by Delegates.observable(arrayListOf()) { _, _, newValue ->
        if (newValue.isNullOrEmpty().not()) {
            actorMoviesAdapter.setActorMoviesList(ArrayList(newValue))
        }
        Log.e("Delegates", "user -> ${newValue}")
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActorMovieBinding.inflate(inflater,container,false)
        return binding.root
    }
    private val retrofit: Retrofit by lazy {
        RetrofitClient.getRetrofit()
    }
    private val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
    private val actorMoviesAdapter:ActorMoviesAdapter by lazy {
        ActorMoviesAdapter()
    }
    private val actorMovieRepository:ActorMovieRepository by lazy {
        ActorMovieRepository(movieApi)
    }
    private val actorMovieViewModel:ActorMovieViewModel by viewModels {
        ActorMovieViewModelFactory(actorMovieRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val main = activity as MainActivity
        main.setBottomNavigationViewVisibility(false)
        actorMovieViewModel.getActorMovies()
        actorMovieInit()
        actorMovieObserve()
    }
    private fun actorMovieInit() {
        with(binding) {
            recyclerActorMovies.apply {
                actorMoviesAdapter.setActorMoviesList(ArrayList(actorMovieList))
                adapter = actorMoviesAdapter
                layoutManager = GridLayoutManager(requireContext(), 3)

            }
        }
    }

    private fun actorMovieObserve(){
        actorMovieViewModel.actorMovieList.observe(viewLifecycleOwner) { actorMovieList ->
            this.actorMovieList = actorMovieList?.cast
        }
    }
}