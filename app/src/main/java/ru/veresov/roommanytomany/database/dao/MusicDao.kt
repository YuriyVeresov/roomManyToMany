package ru.veresov.roommanytomany.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ru.veresov.roommanytomany.database.entity.Music
import ru.veresov.roommanytomany.database.entity.MusicWithPlaylists

@Dao
interface MusicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg music: Music)

    @Transaction
    @Query("SELECT * FROM music")
    fun getMusicWithPlaylists(): List<MusicWithPlaylists>

}