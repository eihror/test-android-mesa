package com.eihror.mesatestigor.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.eihror.mesatestigor.R
import com.eihror.mesatestigor.providers.models.response.News

class NewsAdapter(private val onItemClickedListener: (News) -> Unit) :
    ListAdapter<News, NewsViewHolder>(NewsDiffUtil.getNewsDiffUtilCallback()) {

    fun setList(list: List<News>) {
        this.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_latest_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val element = getItem(position)
        holder.apply {
            bind(element)
            itemView.setOnClickListener {
                onItemClickedListener.invoke(element)
            }
        }
    }

}