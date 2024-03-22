package ru.veresov.roommanytomany.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.veresov.roommanytomany.database.dao.MusicDao
import ru.veresov.roommanytomany.database.dao.MusicPlayListAcrossRefDao
import ru.veresov.roommanytomany.database.dao.PlayListDao
import ru.veresov.roommanytomany.database.dao.UserDao
import ru.veresov.roommanytomany.database.entity.Music
import ru.veresov.roommanytomany.database.entity.MusicPlayListAcrossRef
import ru.veresov.roommanytomany.database.entity.PlayList
import ru.veresov.roommanytomany.database.entity.User

@Database(
    entities = [User::class, Music::class, PlayList::class, MusicPlayListAcrossRef::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun musicDao(): MusicDao
    abstract fun playListDao(): PlayListDao
    abstract fun musicPlayListAcrossRefDao(): MusicPlayListAcrossRefDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    fun populateDatabase() {
        val userDao = userDao()
        val musicDao = musicDao()
        val playListDao = playListDao()
        val musicPlayListAcrossRefDao = musicPlayListAcrossRefDao()

        // Добавление пользователей
        userDao.insert(
            User(1, "John"),
            User(2, "Alice")
        )

        // Добавление музыки
        musicDao.insert(
            Music(1, "Song1"),
            Music(2, "Song2"),
            Music(3, "Song3")
        )

        // Добавление плейлистов
        playListDao.insert(
            PlayList(1, "Favorites"),
            PlayList(2, "Party Mix")
        )

        // Добавление записей в промежуточную таблицу MusicPlayListAcrossRef
        musicPlayListAcrossRefDao.insert(
            MusicPlayListAcrossRef(1, 1),// Песня 1 в плейлисте 1
            MusicPlayListAcrossRef(1, 2),// Песня 2 в плейлисте 1
            MusicPlayListAcrossRef(2, 2),// Песня 2 в плейлисте 2
            MusicPlayListAcrossRef(3, 1),// Песня 3 в плейлисте 1
        )
    }
}
