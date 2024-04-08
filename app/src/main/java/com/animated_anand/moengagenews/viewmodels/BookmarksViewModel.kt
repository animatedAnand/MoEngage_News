package com.animated_anand.moengagenews.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.animated_anand.moengagenews.model.Bookmark
import com.animated_anand.moengagenews.repository.BookmarksRepository
import kotlinx.coroutines.launch

class BookmarksViewModel(private val repository: BookmarksRepository) : ViewModel() {

    // LiveData to observe all bookmarks
    val allBookmarks: LiveData<List<Bookmark>> = repository.allBookmarks

    // Function to insert a bookmark using coroutines
    fun insertBookmark(bookmark: Bookmark) {
        viewModelScope.launch {
            repository.insertBookmark(bookmark)
        }
    }

    // Function to delete a bookmark using coroutines
    fun deleteBookmark(bookmark: Bookmark) {
        viewModelScope.launch {
            repository.deleteBookmark(bookmark)
        }
    }
}
