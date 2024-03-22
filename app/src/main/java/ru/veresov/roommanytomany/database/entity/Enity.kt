package ru.veresov.roommanytomany.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    tableName = "user"
)
data class User(
    @PrimaryKey val userId: Int,
    val name: String,
)

@Entity(
    tableName = "music"
)
data class Music(
    @PrimaryKey val musicId: Int,
    val title: String,
)

@Entity(
    tableName = "play_list"
)
data class PlayList(
    @PrimaryKey val playId: Int,
    val name: String,
)

@Entity(
    tableName = "music_playlist",
    primaryKeys = ["playId", "musicId"]
)
data class MusicPlayListAcrossRef(
    val playId: Int,
    val musicId: Int,
)

@Entity(
    tableName = "playlist_with_music"
)

data class PlayListWithMusic(
    @Embedded val playlist: PlayList,
    @Relation(
        parentColumn = "playId",
        entityColumn = "musicId",
        associateBy = Junction(MusicPlayListAcrossRef::class)
    )
    val songs: List<Music>,
)

@Entity(
    tableName = "music_with_playlist"
)
data class MusicWithPlaylists(
    @Embedded val song: Music,
    @Relation(
        parentColumn = "musicId",
        entityColumn = "playId",
        associateBy = Junction(MusicPlayListAcrossRef::class)
    )
    val playlists: List<PlayList>,
)