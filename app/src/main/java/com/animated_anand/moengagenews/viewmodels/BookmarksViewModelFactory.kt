package com.animated_anand.moengagenews.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.animated_anand.moengagenews.repository.BookmarksRepository

class BookmarksViewModelFactory(private val repository: BookmarksRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarksViewModel::class.java)) {
            // Create and return BookmarksViewModel with the provided repository
            @Suppress("UNCHECKED_CAST")
            return BookmarksViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
