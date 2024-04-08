package com.animated_anand.moengagenews.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.animated_anand.moengagenews.R
import com.animated_anand.moengagenews.adapters.NewsAdapter
import com.animated_anand.moengagenews.databinding.FragmentNewsBinding
import com.animated_anand.moengagenews.model.News
import com.animated_anand.moengagenews.network.FetchNewsTask
import com.animated_anand.moengagenews.utils.Utils
import java.text.SimpleDateFormat
import java.util.Locale

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsItemsList: List<News>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(layoutInflater)
        setNewsItems()
        sortNewsItems()
        return binding.root
    }

    private fun sortNewsItems() {
        val ivSort = binding.ivToggleSort
        ivSort.setOnClickListener {
            if (ivSort.drawable.constantState?.equals(ContextCompat.getDrawable(requireContext(), R.drawable.ic_old_to_new)?.constantState) == true) {
                ivSort.setImageResource(R.drawable.ic_new_to_old)
                // Sort the news items list in descending order based on publishedAt
                newsItemsList.sortedWith(Comparator { news1, news2 ->
                    news1.publishedAt.compareTo(news2.publishedAt)
                })
            }
            else {
                ivSort.setImageResource(R.drawable.ic_old_to_new)
                newsItemsList.sortedWith(Comparator { news1, news2 ->
                    news1.publishedAt.compareTo(news2.publishedAt)
                })
            }
            diplayNewsItems(newsItemsList)
        }
    }

    private fun setNewsItems() {
        Utils.showProgressDialog(requireContext(),"Loading latest News..")
        // Create an instance of FetchNewsTask with a listener
        val fetchNewsTask = FetchNewsTask(object : FetchNewsTask.OnFetchCompleteListener {
            override fun onFetchComplete(newsItems: List<News>?) {
                // Check if newsItems is not null and update the RecyclerView
                newsItems?.let {
                    newsItemsList = newsItems
                    Utils.hideProgressDialog()
                    diplayNewsItems(newsItemsList)
                }
            }
        })

        // Execute the task to fetch news items
        fetchNewsTask.execute()
    }

    private fun diplayNewsItems(newsItemsList: List<News>) {
        val layoutManager = LinearLayoutManager(context)
        binding.rvAllNews.layoutManager = layoutManager

        val adapter = NewsAdapter(newsItemsList,requireContext())
        binding.rvAllNews.adapter = adapter
    }

}