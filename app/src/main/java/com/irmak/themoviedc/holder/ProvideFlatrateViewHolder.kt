package com.irmak.themoviedc.holder

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.ProvidersLayoutBinding
import com.irmak.themoviedc.model.watchProviders.Provider
import com.irmak.themoviedc.ui.Fragment.DetailFragmentDirections
import com.irmak.themoviedc.ui.Fragment.layoutCheck
import com.irmak.themoviedc.ui.Fragment.providersId
import com.irmak.themoviedc.ui.extensions.loadImage

class ProvideFlatrateViewHolder(val binding: ProvidersLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun provideBind(response: Provider) {
        if (response.logo_path != null) {
            binding.posterViewProvider.loadImage("https://image.tmdb.org/t/p/original/${response.logo_path}")
        }
        binding.cardViewProvider.setOnClickListener {
            layoutCheck = 2
            providersId = response.provider_id
            val action = DetailFragmentDirections.actionDetailFragmentToPriceDetailFragment()
            binding.root.findNavController().navigate(action)
        }
    }

}