package com.eihror.mesatestigor.presentation.home

import androidx.recyclerview.widget.DiffUtil
import com.eihror.mesatestigor.providers.models.response.News

object NewsDiffUtil {
    fun getNewsDiffUtilCallback() = object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }

    }
}