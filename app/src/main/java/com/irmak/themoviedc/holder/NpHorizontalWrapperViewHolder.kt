package com.irmak.themoviedc.holder

import android.os.Handler
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.ConcatRecyclerBinding
import java.util.*

class NpHorizontalWrapperViewHolder (
    private val binding: ConcatRecyclerBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(adapter: RecyclerView.Adapter<*>, lastScrollX: Int, onScrolled: (Int) -> Unit) {
        val context = binding.root.context
        binding.apply {
            val layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            concatRecyclerview.layoutManager = layoutManager
            concatRecyclerview.adapter = adapter
            concatRecyclerview.doOnPreDraw {
                concatRecyclerview.scrollBy(lastScrollX, 0)
            }
            concatRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    onScrolled(recyclerView.computeHorizontalScrollOffset())
                }
            })
        }
    }
}
