package ru.veresov.roommanytomany

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.veresov.roommanytomany.database.AppDatabase

private const val TAG = "DEBUG"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val database = AppDatabase.getDatabase(this)
        database.populateDatabase()
        val playListWithMusics = database.playListDao().getPlaylistsWithMusic()
        val musicWithPlayList = database.musicDao().getMusicWithPlaylists()

        Log.d(TAG, "playListWithMusics $playListWithMusics")
        Log.d(TAG, "musicWithPlayList $musicWithPlayList")
    }
}