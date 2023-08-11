package com.irmak.themoviedc.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.irmak.themoviedc.databinding.SearchLayoutGridBinding
import com.irmak.themoviedc.holder.SearchViewHolder
import com.irmak.themoviedc.model.search.SearchResponse

class SearchAdapter:RecyclerView.Adapter<SearchViewHolder>() {
    var searchList: ArrayList<SearchResponse>? = null
    fun setSearchLst(searchList: ArrayList<SearchResponse>) {
        this.searchList = searchList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding =
            SearchLayoutGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int {
            return searchList!!.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
            holder.searchBind(searchList!!.get(position))
    }
}