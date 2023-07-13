package com.irmak.themoviedc.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.TrendLayoutBinding
import com.irmak.themoviedc.holder.TrendViewHolder
import com.irmak.themoviedc.model.trendAll.TrendResponse

class TrendAdapter: RecyclerView.Adapter<TrendViewHolder>() {

    var TrendList: ArrayList<TrendResponse>? = null

    fun setTrendAllList(TrendList: ArrayList<TrendResponse>?) {
        this.TrendList = TrendList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        val binding =
            TrendLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        holder.trendBind(TrendList!!.get(position))
    }

    override fun getItemCount(): Int {
        return TrendList?.size ?: 0
    }

}
