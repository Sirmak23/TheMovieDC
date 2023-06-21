package com.irmak.themoviedc.holder

import android.annotation.SuppressLint
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.R
import com.irmak.themoviedc.data.remote.api.movieIdNumber
import com.irmak.themoviedc.databinding.FragmentNowPlayingGridLayoutBinding
import com.irmak.themoviedc.model.nowPlayingModel.ResultNP
import com.irmak.themoviedc.ui.Fragment.NowPlayingFragment
import com.irmak.themoviedc.ui.Fragment.NowPlayingFragmentDirections
import com.irmak.themoviedc.ui.Fragment.PopularFragmentDirections
import com.irmak.themoviedc.ui.extensions.loadImage

class NowPlayingViewHolder(val binding: FragmentNowPlayingGridLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val nowPlayingFragment = NowPlayingFragment()


    @SuppressLint("SetTextI18n")
    fun NpBind(response: ResultNP) {
        binding.txtDateNP.text = response.release_date
        binding.posterViewNP.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response.poster_path}")
        binding.imdbPhotoNP.loadImage("https://m.media-amazon.com/images/G/01/imdb/images/social/imdb_logo._CB410901634_.png")
        binding.txtTitleNP.text = response.original_title
        binding.txtVoteAvarageNP.text = "${response.vote_average}/10"
        if (response.overview.isNullOrEmpty().not()) {
            binding.txtOverViewNP.text = response.overview
        } else
            binding.txtOverViewNP.text = "Üzgünüz özet bilgisi bulunamadı"
        binding.txtOverViewNP.setOnClickListener {
            val alertDialogBuilder =
                AlertDialog.Builder(itemView.context, R.style.TransparentDialogStyle)
            val alertDialog = alertDialogBuilder.create()
//            alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alertDialogBuilder.setMessage("${response.overview}")
            alertDialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
            alertDialogBuilder.setPositiveButton("KAPAT") { dialog, which -> dialog.dismiss() }
            alertDialogBuilder.show()
        }
        binding.cardViewNP.setOnClickListener {
            movieIdNumber = response.id
            fragmentChoice(frm)
        }
    }

    private fun fragmentChoice(fragmentName: String?) {
        if (fragmentName == "popular") {
            val action = PopularFragmentDirections.actionPopularFragmentToDetailFragment()
            itemView.findNavController().navigate(action)
        } else if (fragmentName == "nowPlaying") {
            val action2 = NowPlayingFragmentDirections.actionNowPlayingFragmentToDetailFragment()
            itemView.findNavController().navigate(action2)
        }
    }

}

var frm: String? = null

// alert dialog oluşturmak için kod
//val builder = AlertDialog.Builder(itemView.context)
//builder.setMessage("Bilet almak için 'paribucineverse.com'a gitmek ister misiniz?")
//builder.setPositiveButton("Kapat") { dialog, which -> dialog.dismiss() }
//builder.setNegativeButton("Bilet Al") { dialog, which ->
//    val url = "https://www.paribucineverse.com/vizyondakiler"
//    val intent = Intent(Intent.ACTION_VIEW)
//    intent.data = Uri.parse(url)
//    itemView.context.startActivity(intent)
//}
//builder.show()