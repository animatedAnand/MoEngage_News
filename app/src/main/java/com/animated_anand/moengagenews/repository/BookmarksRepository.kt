package com.animated_anand.moengagenews.repository

import androidx.lifecycle.LiveData
import com.animated_anand.moengagenews.dao.BookmarksDao
import com.animated_anand.moengagenews.model.Bookmark

class BookmarksRepository(private val bookmarksDao: BookmarksDao) {

    // LiveData to observe all bookmarks
    val allBookmarks: LiveData<List<Bookmark>> = bookmarksDao.getAllBookmarks()

    // Coroutine function to insert a bookmark into the database
    suspend fun insertBookmark(bookmark: Bookmark) {
        bookmarksDao.insertBookmark(bookmark)
    }

    // Coroutine function to delete a bookmark from the database
    suspend fun deleteBookmark(bookmark: Bookmark) {
        bookmarksDao.deleteBookmark(bookmark)
    }
}
