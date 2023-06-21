package com.irmak.themoviedc.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ConcatViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(adapter: T)
}