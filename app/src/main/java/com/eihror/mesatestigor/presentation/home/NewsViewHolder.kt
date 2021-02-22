package com.eihror.mesatestigor.presentation.home

import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.eihror.mesatestigor.R
import com.eihror.mesatestigor.providers.models.response.News

class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val context: Context = view.context

    val image: AppCompatImageView = view.findViewById(R.id.image_view_latest_news)
    val checkbox: AppCompatCheckBox = view.findViewById(R.id.checkbox_latest_news)
    val timeAgo: AppCompatTextView = view.findViewById(R.id.text_view_latest_news_time)
    val title: AppCompatTextView = view.findViewById(R.id.text_view_latest_news_title)
    val description: AppCompatTextView = view.findViewById(R.id.text_view_latest_news_description)

    fun bind(element: News) {
        title.text = element.title
        description.text = element.description
    }
}