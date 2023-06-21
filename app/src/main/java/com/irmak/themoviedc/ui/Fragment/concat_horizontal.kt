package com.irmak.themoviedc.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.irmak.themoviedc.databinding.FragmentConcatHorizontalBinding
class concat_horizontal : Fragment() {
    private lateinit var binding: FragmentConcatHorizontalBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConcatHorizontalBinding.inflate(inflater,container,false)
        return binding.root
    }
}