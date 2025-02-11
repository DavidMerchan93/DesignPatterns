package behaviour.iterator

data class Song(
    val title: String,
    val artist: String,
    val duration: Int
)

/**
 * An interface defining the behavior of an iterator for a collection of type [T].
 *
 * @param T The type of elements the iterator will traverse.
 */
interface Iterator<T> {
    fun next(): T?
    fun hasNext(): Boolean
    fun current(): T?
}

/**
 * A class representing a playlist of songs.
 *
 * This class allows adding songs to the playlist, retrieving songs at specific indices,
 * checking if there are more songs, and creating an iterator to traverse and access the
 * songs in the playlist.
 */
class PlayList {

    private val songs = mutableSetOf<Song>()

    fun addSong(song: Song) {
        songs.add(song)
    }

    fun getSongAt(index: Int): Song? {
        if (index >= 0 && index < songs.size)
            return songs.elementAt(index)
        return null
    }

    fun hasNext(current: Int): Boolean {
        return current < songs.size
    }

    fun createIterator(): Iterator<Song> {
        return SongIterator(this)
    }
}

/**
 * An iterator for the [PlayList] class, allowing traversal and access to the songs in the playlist.
 *
 * @param playList The playlist to iterate over.
 */
class SongIterator(private val playList: PlayList) : Iterator<Song> {

    private var current = 0

    override fun next(): Song? {
        return playList.getSongAt(current++)
    }

    override fun hasNext(): Boolean {
        return playList.hasNext(current)
    }

    override fun current(): Song? {
        return playList.getSongAt(current)
    }
}


fun main() {
    val playList = PlayList()
    val songsIterator = playList.createIterator()

    playList.addSong(Song("Song 1", "Artist 1", 300))
    playList.addSong(Song("Song 2", "Artist 2", 250))
    playList.addSong(Song("Song 3", "Artist 3", 400))
    playList.addSong(Song("Song 4", "Artist 2", 330))
    playList.addSong(Song("Song 5", "Artist 5", 550))
    playList.addSong(Song("Song 6", "Artist 1", 209))

    while (songsIterator.hasNext()) {
        val song = songsIterator.next()
        println("Estas escuchando: ${song?.title} de ${song?.artist} y dura ${song?.duration} seconds.")
    }
}
