package com.animated_anand.moengagenews.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.animated_anand.moengagenews.adapters.BookmarksAdapter
import com.animated_anand.moengagenews.database.AppDatabase
import com.animated_anand.moengagenews.databinding.FragmentBookmarksBinding
import com.animated_anand.moengagenews.repository.BookmarksRepository
import com.animated_anand.moengagenews.viewmodels.BookmarksViewModel
import com.animated_anand.moengagenews.viewmodels.BookmarksViewModelFactory

class BookmarksFragment : Fragment() {

    private lateinit var bookmarksViewModel: BookmarksViewModel
    private lateinit var bookmarksAdapter: BookmarksAdapter
    private lateinit var binding: FragmentBookmarksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBookmarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create or inject your repository instance
        val repository = BookmarksRepository(AppDatabase.getDatabase(requireContext()).bookmarksDao())

        // Create ViewModelFactory with the repository
        val viewModelFactory = BookmarksViewModelFactory(repository)

        // Initialize ViewModel using ViewModelFactory
        bookmarksViewModel = ViewModelProvider(this, viewModelFactory).get(BookmarksViewModel::class.java)

        // Initialize RecyclerView and Adapter
        bookmarksAdapter = BookmarksAdapter()
        binding.rvAllBookmarks.apply {
            adapter = bookmarksAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        // Observe LiveData from ViewModel
        bookmarksViewModel.allBookmarks.observe(viewLifecycleOwner, Observer { bookmarks ->
            bookmarks?.let {
                bookmarksAdapter.setBookmarksList(it)
            }
        })
    }
}
