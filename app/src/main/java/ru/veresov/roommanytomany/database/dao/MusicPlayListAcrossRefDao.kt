package ru.veresov.roommanytomany.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import ru.veresov.roommanytomany.database.entity.MusicPlayListAcrossRef
@Dao
interface MusicPlayListAcrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg crossRef: MusicPlayListAcrossRef)
}