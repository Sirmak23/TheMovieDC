package com.irmak.themoviedc.holder

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.irmak.themoviedc.databinding.TvEpisodesLayoutBinding
import com.irmak.themoviedc.model.seasonInfoModel.EpisodeData
import com.irmak.themoviedc.ui.extensions.loadImage

class SeasonInfoViewHolder(val binding:TvEpisodesLayoutBinding):RecyclerView.ViewHolder(binding.root) {
    var overView:String = "ÖZET BULUNAMADI"
    @SuppressLint("SetTextI18n")
    fun SeasonBind(response: com.irmak.themoviedc.model.seasonInfoModel.EpisodeData){
        binding.episodePoster.loadImage("https://www.themoviedb.org/t/p/w500_and_h282_bestv2${response.still_path}")
        binding.episodeName.text = response.name
        binding.seasonAndEpisodeNo.text = "S:${response.season_number} B:${response.episode_number}"
        binding.seasonInfoCard.setOnClickListener {
            if (!response.overview.isNullOrEmpty()){
                overView = response.overview.toString()
            } else{
                overView = "ÖZET BULUNAMADI"

            }
            SweetAlertDialog(itemView.context, SweetAlertDialog.NORMAL_TYPE)
                .setTitleText("Bölüm özeti: ${overView}")
//            .setContentText("İnternet bağlantısı için wifi ayarlarına gitmek ister misiniz?")
                .show()
        }
    }
}
