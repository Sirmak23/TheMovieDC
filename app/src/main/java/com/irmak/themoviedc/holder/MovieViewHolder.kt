package com.irmak.themoviedc.holder

import android.annotation.SuppressLint
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.data.remote.api.movieIdNumber
import com.irmak.themoviedc.databinding.FragmentGridLayoutBinding
import com.irmak.themoviedc.model.popularModel.MovieRespons
import com.irmak.themoviedc.ui.Fragment.PopularFragmentDirections
import com.irmak.themoviedc.ui.extensions.loadImage

class MovieViewHolder(val binding: FragmentGridLayoutBinding):RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(response: MovieRespons) {
        binding.txtTitleRG.text = response.title
        binding.posterView3.loadImage("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${response.poster_path}")

        binding.cardView.setOnClickListener {
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


        }


    }
}