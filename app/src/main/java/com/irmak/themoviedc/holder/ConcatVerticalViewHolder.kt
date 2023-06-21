package com.irmak.themoviedc.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ConcatVerticalViewHolder <T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(adapter: T)
}