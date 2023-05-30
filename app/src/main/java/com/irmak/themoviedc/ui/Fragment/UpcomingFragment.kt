package com.irmak.themoviedc.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.irmak.themoviedc.R
import com.irmak.themoviedc.adapter.UpcomingAdapter
import com.irmak.themoviedc.data.remote.api.MovieApi
import com.irmak.themoviedc.databinding.FragmentUpcomingBinding
import com.irmak.themoviedc.model.UpcomingModel.ResultUP
import com.irmak.themoviedc.repository.UpcomingRepository
import com.irmak.themoviedc.retrofit.RetrofitClient
import com.irmak.themoviedc.viewModel.UpcomingViewModel
import com.irmak.themoviedc.viewModel.viewModelFactory.UpcomingViewModelFactory
import retrofit2.Retrofit
import kotlin.properties.Delegates

class UpcomingFragment : Fragment() {
    var upcomingList:List<ResultUP>? by Delegates.observable(arrayListOf()){_,_, newValue->
        if (newValue.isNullOrEmpty().not()){
            upcomingAdapter.setUpcomingList(ArrayList(newValue))
        }
    }
private lateinit var binding:FragmentUpcomingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpcomingBinding.inflate(inflater,container,false)
        return binding.root
    }

    private val upcomingAdapter:UpcomingAdapter by lazy {
        UpcomingAdapter()
    }
    private val retrofit:Retrofit by lazy {
        RetrofitClient.getRetrofit()
    }
    private val movieApi:MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
    private val upcomingRepository:UpcomingRepository by lazy {
        UpcomingRepository(movieApi)
    }
    private val upcomingViewModel:UpcomingViewModel by viewModels{
        UpcomingViewModelFactory(upcomingRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        upcomingViewModel.getUpcomingMovie()
        initUpcoming()
        upComingObserve()
        upToPageUp()
    }
    private fun initUpcoming(){
        with(binding){
            recyclerViewUpcoming.apply {
                adapter= upcomingAdapter
                recyclerViewUpcoming.layoutManager = GridLayoutManager(requireContext(),1)
            }
        }
    }
    private fun upComingObserve(){
        upcomingViewModel.upcomingList.observe(viewLifecycleOwner){upcomingList->
            this.upcomingList = upcomingList.results
        }
    }
    private fun upToPageUp(){
        binding.upButtonUpcoming.setOnClickListener{
            binding.recyclerViewUpcoming.smoothScrollToPosition(0)
        }
    }
}