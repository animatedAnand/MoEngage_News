package com.animated_anand.moengagenews.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.animated_anand.moengagenews.R
import com.animated_anand.moengagenews.database.AppDatabase
import com.animated_anand.moengagenews.databinding.ItemNewsBinding
import com.animated_anand.moengagenews.model.Bookmark
import com.animated_anand.moengagenews.model.News
import com.animated_anand.moengagenews.utils.Utils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsAdapter(private val newsList: List<News>, private val context: Context) : RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder>() {

    // ViewHolder for news items
    class NewsItemViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        // Inflate the item layout and create ViewHolder
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val news = newsList[position]
        holder.binding.apply {
            // Bind news data to views
            tvNewsSource.text = news.sourceName
            tvTitle.text = news.title
            tvTime.text = news.publishedAt.substring(0, 10)

            // Load image using Glide library
            Glide.with(holder.binding.root)
                .load(news.urlToImage)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_news)) // Placeholder image resource
                .apply(RequestOptions.errorOf(R.drawable.ic_bookmarks)) // Error image resource
                .into(ivNews)
        }

        // Click listener for opening news article in browser
        holder.binding.tvTitle.setOnClickListener {
            var url = news.url
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://$url"
            }
            val query = Uri.encode(url, "UTF-8")
            val browserIntent = Intent(Intent.CATEGORY_BROWSABLE, Uri.parse(Uri.decode(query)))
            browserIntent.action = Intent.ACTION_VIEW
            context.startActivity(browserIntent)
        }

        // Click listener for bookmarking news
        holder.binding.ivToggleBookmark.setOnClickListener {
            if (news.isBookmarked == false) {
                holder.binding.ivToggleBookmark.setColorFilter(ContextCompat.getColor(context, R.color.bookmarkedColor))
                news.isBookmarked == true
                val bookmarkDao = AppDatabase.getDatabase(context).bookmarksDao()
                val bookmark = Bookmark(
                    sourceId = news.sourceId,
                    sourceName = news.sourceName,
                    author = news.author,
                    title = news.title,
                    description = news.description,
                    url = news.url,
                    urlToImage = news.urlToImage,
                    publishedAt = news.publishedAt,
                    content = news.content
                )
                // Insert bookmark into database using coroutines
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        bookmarkDao.insertBookmark(bookmark)
                        //Utils.showToast(context,"News Bookmarked!")
                    } catch (e: Exception) {
                        //Utils.showToast(context,"Something went wrong!")
                    }
                }
            } else {
                holder.binding.ivToggleBookmark.setColorFilter(ContextCompat.getColor(context, R.color.unBookmarkedColor))
            }
        }

        // Click listener for sharing news
        holder.binding.ivShareNews.setOnClickListener {
            // Create a share intent
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, news.url)

            // Create a chooser to show available sharing apps
            val chooserIntent = Intent.createChooser(shareIntent, "Share news via")

            // Check if there are apps that can handle the intent
            if (shareIntent.resolveActivity(context.packageManager) != null) {
                // Start the chooser activity
                context.startActivity(chooserIntent)
            } else {
                // Handle the case where no apps can handle the intent
                Utils.showToast(context, "No apps available for sharing")
            }
        }
    }
}
