package com.irmak.themoviedc.holder

import android.annotation.SuppressLint
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.R
import com.irmak.themoviedc.data.remote.api.movieIdNumber
import com.irmak.themoviedc.databinding.FragmentGridLayoutBinding
import com.irmak.themoviedc.model.popularModel.MovieRespons
import com.irmak.themoviedc.ui.Fragment.PopularFragmentDirections
import com.irmak.themoviedc.ui.extensions.loadImage
import java.util.*

class MovieViewHolder(val binding: FragmentGridLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(response: MovieRespons) {
        binding.txtTitleRG.text = response.title
        binding.posterView3.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response.poster_path}")
        binding.txtDateM.text = response.release_date
        binding.imdbPhotoM.loadImage("https://m.media-amazon.com/images/G/01/imdb/images/social/imdb_logo._CB410901634_.png")
        binding.txtOverViewM.text = "${response.vote_average}/10"
        if (response.overview.isNullOrEmpty().not()) {
            binding.txtOverViewM.text = response.overview
        } else
            binding.txtOverViewM.text = "Üzgünüz özet bilgisi bulunamadı"

        binding.txtOverViewM.setOnClickListener {
            val alertDialogBuilder =
                AlertDialog.Builder(itemView.context, R.style.TransparentDialogStyle)
            val alertDialog = alertDialogBuilder.create()
//            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialogBuilder.setMessage("${response.overview}")
            alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
            alertDialogBuilder.setPositiveButton("KAPAT") { dialog, which -> dialog.dismiss() }
            alertDialogBuilder.show()
        }

        binding.cardViewM.setOnClickListener {
            movieIdNumber = response.id
            val action = PopularFragmentDirections.actionPopularFragmentToDetailFragment()
            itemView.findNavController().navigate(action)
//            itemView.findViewById<ImageView>(R.id.popularBackGround).loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response.backdrop_path}")


//            val builder = AlertDialog.Builder(itemView.context)
//            builder.setMessage("${response.overview}")
//            builder.setPositiveButton("Kapat") { dialog, which -> dialog.dismiss() }
//            builder.setNegativeButton("Tarayıcıda aç") { dialog, which ->
//                val url = "https://www.themoviedb.org/movie/${response.id}"
//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.data = Uri.parse(url)
//                itemView.context.startActivity(intent)
//            }
//            builder.show()

//            val url = "https://www.themoviedb.org/movie/${response.id}"
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse(url)
//            itemView.context.startActivity(intent)
//         tür seçimi
//        if (response.genre_ids!!.contains(80) || response.genre_ids.contains(28)) {
//            layoutParams.width = RecyclerView.LayoutParams.WRAP_CONTENT
//            layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT
//            binding.root.visibility = View.VISIBLE
//        } else {
//            layoutParams.width = 0
//            layoutParams.height = 0
//            binding.root.visibility = View.GONE
//        }
//        binding.root.layoutParams = layoutParams

        }


    }
}