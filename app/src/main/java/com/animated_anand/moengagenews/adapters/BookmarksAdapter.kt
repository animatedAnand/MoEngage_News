package com.animated_anand.moengagenews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.animated_anand.moengagenews.databinding.ItemNewsBinding
import com.animated_anand.moengagenews.model.Bookmark
import com.animated_anand.moengagenews.model.News

class BookmarksAdapter() : RecyclerView.Adapter<BookmarksAdapter.ViewHolder>() {
    // List to hold bookmark items
    private var bookmarkList: List<Bookmark> = ArrayList()

    // Create ViewHolder for RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate item layout and return ViewHolder
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // Bind data to ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get bookmark item at current position
        val newsItem = bookmarkList[position]
        // Bind bookmark item to ViewHolder
        holder.bind(newsItem)
    }

    // Get total item count
    override fun getItemCount(): Int {
        return bookmarkList.size
    }

    // Set the list of bookmarks to display
    fun setBookmarksList(newsList: List<Bookmark>) {
        this.bookmarkList = bookmarkList
        notifyDataSetChanged()
    }

    // ViewHolder class to hold item views
    inner class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        // Bind bookmark item to views
        fun bind(newsItem: Bookmark) {
            binding.apply {
                // Set title, source, and time text
                tvTitle.text = newsItem.title
                tvNewsSource.text = newsItem.sourceName
                tvTime.text = newsItem.publishedAt
            }
        }
    }
}
