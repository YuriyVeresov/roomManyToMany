package ru.veresov.roommanytomany.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.veresov.roommanytomany.database.entity.PlayList
import ru.veresov.roommanytomany.database.entity.PlayListWithMusic

@Dao
interface PlayListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg playlist: PlayList)

    @Transaction
    @Query("SELECT * FROM play_list")
    fun getPlaylistsWithMusic(): List<PlayListWithMusic>
}