package com.irmak.themoviedc.adapter

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.R
import com.irmak.themoviedc.databinding.ConcatVerticalRecylclerBinding
import com.irmak.themoviedc.holder.ConcatVerticalViewHolder
import kotlinx.coroutines.withContext
import java.util.*

class GridConcatAdapter(private val movieAdapter: MovieAdapter) :
    RecyclerView.Adapter<ConcatVerticalViewHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConcatVerticalViewHolder<*> {
        val binding = ConcatVerticalRecylclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        binding.concatVerticalRecyclerview.layoutManager = GridLayoutManager(parent.context, 2)
        return popularMovieHolder(binding.root)
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: ConcatVerticalViewHolder<*>, position: Int) {
        when (holder) {
            is popularMovieHolder -> holder.bind(movieAdapter)
        }
    }

    inner class popularMovieHolder(itemView: View) : ConcatVerticalViewHolder<MovieAdapter>(itemView) {
        override fun bind(adapter: MovieAdapter) {
            val concatVerticalRecycler =
                itemView.findViewById<RecyclerView>(R.id.concatVerticalRecyclerview)
            concatVerticalRecycler.adapter = adapter
        }
    }

}