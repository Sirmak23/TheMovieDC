package com.irmak.themoviedc.holder

import android.annotation.SuppressLint
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.R
import com.irmak.themoviedc.data.remote.api.movieIdNumber
import com.irmak.themoviedc.databinding.SearchLayoutGridBinding
import com.irmak.themoviedc.model.search.SearchResponse
import com.irmak.themoviedc.ui.Fragment.PopularFragmentDirections
import com.irmak.themoviedc.ui.Fragment.SearchFragmentDirections
import com.irmak.themoviedc.ui.extensions.loadImage

class SearchViewHolder(val binding: SearchLayoutGridBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun searchBind(result: SearchResponse) {
        binding.txtTitleS.text = result.title
        binding.posterViewS.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${result.poster_path}")
        binding.txtDateS.text = result.release_date
        binding.imdbPhotoS.loadImage("https://m.media-amazon.com/images/G/01/imdb/images/social/imdb_logo._CB410901634_.png")
        binding.txtOverViewS.text = "${result.vote_average}/10"
        if (result?.overview.isNullOrEmpty().not()) {
            binding.txtOverViewS.text = result.overview
        } else
            binding.txtOverViewS.text = "Üzgünüz özet bilgisi bulunamadı"

        binding.txtOverViewS.setOnClickListener {
            val alertDialogBuilder =
                AlertDialog.Builder(itemView.context, R.style.TransparentDialogStyle)
            val alertDialog = alertDialogBuilder.create()
//            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialogBuilder.setMessage("${result.overview}")
            alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
            alertDialogBuilder.setPositiveButton("KAPAT") { dialog, which -> dialog.dismiss() }
            alertDialogBuilder.show()
        }

        binding.cardViewS.setOnClickListener {
            movieIdNumber = result?.id
            val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment()
            itemView.findNavController().navigate(action)
        }
    }
}