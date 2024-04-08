package com.animated_anand.moengagenews.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.animated_anand.moengagenews.model.Bookmark

@Dao
interface BookmarksDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBookmark(bookmark: Bookmark)

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)

    @Query("SELECT * FROM bookmarks")
    fun getAllBookmarks(): LiveData<List<Bookmark>>
}
