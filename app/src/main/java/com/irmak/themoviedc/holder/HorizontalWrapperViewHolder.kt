package com.irmak.themoviedc.holder

import android.os.Handler
import androidx.core.view.doOnPreDraw
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.FragmentConcatHorizontalBinding
import java.util.*

class HorizontalWrapperViewHolder(
    private val binding: FragmentConcatHorizontalBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(adapter: RecyclerView.Adapter<*>, lastScrollX: Int, onScrolled: (Int) -> Unit) {
        val context = binding.root.context
        binding.apply {
           val layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            ConRecyclerView.layoutManager = layoutManager
            ConRecyclerView.adapter = adapter
            ConRecyclerView.doOnPreDraw {
                ConRecyclerView.scrollBy(lastScrollX, 0)
            }
            ConRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    onScrolled(recyclerView.computeHorizontalScrollOffset())
                }
            })
            val timer = Timer()
            val handler = Handler()
            val runnable = object : Runnable {
                override fun run() {
                    val currentPosition = layoutManager.findFirstVisibleItemPosition()
                    val nextPosition =
                        if (currentPosition == layoutManager.itemCount - 1) {
                            0
                        } else currentPosition + 1
                    binding.ConRecyclerView.smoothScrollToPosition(nextPosition)
//                    if (nextPosition == 0) {
//                        pageNumber += 1
//                    }
                    handler.postDelayed(this, 5000) // 5 saniye
                }
            }
            timer.schedule(object : TimerTask() {
                override fun run() {
                    handler.post(runnable)
                }
            }, 5000) // 5 saniye
        }
    }
}
