package com.irmak.themoviedc.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.ConcatRecyclerBinding
import com.irmak.themoviedc.holder.NpHorizontalWrapperViewHolder

class NpHorizontalWrapperAdapter(
    private val adapter: RecyclerView.Adapter<*>
) : RecyclerView.Adapter<NpHorizontalWrapperViewHolder>() {
    private var lastScrollX = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NpHorizontalWrapperViewHolder {
        return NpHorizontalWrapperViewHolder(
            ConcatRecyclerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        return STORE_HORIZONTAL_WRAPPER_VIEW_TYPE
    }

    override fun onBindViewHolder(holder: NpHorizontalWrapperViewHolder, position: Int) {
        holder.bind(adapter, lastScrollX) { x ->
            lastScrollX = x
        }
    }

    override fun getItemCount(): Int = 1

    fun onSaveState(outState: Bundle) {
        outState.putInt(KEY_SCROLL_X, lastScrollX)
    }

    fun onRestoreState(savedState: Bundle?) {
        lastScrollX = savedState?.getInt(KEY_SCROLL_X) ?: 0
    }

    companion object {
        private const val KEY_SCROLL_X = "com.performa.pickslam.ui.store.concatadapter.key_scroll_x"
        const val STORE_HORIZONTAL_WRAPPER_VIEW_TYPE = 2222
    }
}