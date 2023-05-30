package com.irmak.themoviedc.holder

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentNowPlayingGridLayoutBinding
import com.irmak.themoviedc.model.nowPlayingModel.ResultNP
import com.irmak.themoviedc.ui.extensions.loadImage
import kotlin.concurrent.fixedRateTimer
import kotlin.system.exitProcess

class NowPlayingViewHolder(val binding: FragmentNowPlayingGridLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun NpBind(response: ResultNP) {
        binding.txtDateNP.text = response.release_date
        binding.posterViewNP.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response.poster_path}")
        binding.imdbPhotoNP.loadImage("https://m.media-amazon.com/images/G/01/imdb/images/social/imdb_logo._CB410901634_.png")
        binding.txtTitleNP.text = response.title
        binding.txtVoteAvarageNP.text = "${response.vote_average}/10"
        if (response.overview.isNullOrEmpty().not()){
            binding.txtOverViewNP.text = response.overview
        }else
            binding.txtOverViewNP.text = "Üzgünüz özet bilgisi bulunamadı"
        binding.cardViewNP.setOnClickListener {
            val builder = AlertDialog.Builder(itemView.context)
            builder.setMessage("Bilet almak için 'paribucineverse.com'a gitmek ister misiniz?")
            builder.setPositiveButton("Kapat") { dialog, which -> dialog.dismiss() }
            builder.setNegativeButton("Bilet Al") { dialog, which ->
                val url = "https://www.paribucineverse.com/vizyondakiler"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                itemView.context.startActivity(intent)
            }
            builder.show()
        }
    }
}