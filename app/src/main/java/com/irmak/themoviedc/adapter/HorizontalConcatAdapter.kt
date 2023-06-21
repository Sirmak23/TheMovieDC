package com.irmak.themoviedc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.R
import com.irmak.themoviedc.databinding.ConcatRecyclerBinding
import com.irmak.themoviedc.holder.ConcatViewHolder

class HorizontalConcatAdapter(private val context: Context, private val nowComingAdapter: NowPlayingAdapter) :
    RecyclerView.Adapter<ConcatViewHolder<*>>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConcatViewHolder<*> {
    val binding = ConcatRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        binding.concatRecyclerview.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        return myUpComingMovie(binding.root)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: ConcatViewHolder<*>, position: Int) {
        when(holder){
            is myUpComingMovie -> holder.bind(nowComingAdapter)
            else -> throw IllegalArgumentException("No viewholder to show this data, did you forgot to add it to the onBindViewHolder?")
        }
    }

    inner class myUpComingMovie(itemView: View): ConcatViewHolder<NowPlayingAdapter>(itemView){
        override fun bind(adapter: NowPlayingAdapter) {
            val concRecyclerView = itemView.findViewById<RecyclerView>(R.id.concatRecyclerview)
            concRecyclerView.adapter = adapter
        }
    }

}