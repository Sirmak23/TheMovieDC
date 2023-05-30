package com.irmak.themoviedc.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.irmak.themoviedc.R
import com.irmak.themoviedc.databinding.FragmentActorLayoutBinding

class ActorLayout : Fragment() {

    private lateinit var binding: FragmentActorLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActorLayoutBinding.inflate(inflater,container,false)
        return binding.root
    }
}