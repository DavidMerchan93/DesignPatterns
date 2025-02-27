package behaviour.visitor

sealed interface MediaFileDummy {
    data class Image(val width: Int, val height: Int): MediaFileDummy
    data class Video(val duration: Int, val size: Int): MediaFileDummy
    data class Audio(val artist: String, val size: Int): MediaFileDummy
}

interface MediaFile {
    fun accept(visitor: MediaFileVisitor)
}

class ImageFile(val image: MediaFileDummy.Image): MediaFile {
    override fun accept(visitor: MediaFileVisitor) {
        // Calcular el tamaño de las imagenes
        visitor.visit(this)
    }
}

class VideoFile(val video: MediaFileDummy.Video): MediaFile {
    override fun accept(visitor: MediaFileVisitor) {
        // Calcular la duracion total de los videos
        visitor.visit(this)
    }
}

class AudioFile(val audioFile: MediaFileDummy.Audio): MediaFile {
    override fun accept(visitor: MediaFileVisitor) {
        // Calcular el tamaño en megabites
        visitor.visit(this)
    }
}

interface MediaFileVisitor {
    fun visit(imageFile: ImageFile)
    fun visit(videoFile: VideoFile)
    fun visit(audioFile: AudioFile)
}

class ResolutionVisitor: MediaFileVisitor {
    override fun visit(imageFile: ImageFile) {
        val resolution = with(imageFile.image) {
            width * height
        }

        println("La resolucion de la imagen es de: $resolution px")
    }

    override fun visit(videoFile: VideoFile) {
        println("No es una imagen")
    }

    override fun visit(audioFile: AudioFile) {
        println("No es una imagen")
    }
}

class DurationVisitor: MediaFileVisitor {
    override fun visit(imageFile: ImageFile) {
        println("No es un video")
    }

    override fun visit(videoFile: VideoFile) {
        println("La duracion del video es de ${videoFile.video.duration}")
    }

    override fun visit(audioFile: AudioFile) {
        println("No es un video")
    }
}

class SizeVisitor: MediaFileVisitor {
    override fun visit(imageFile: ImageFile) {
        println("No es un Audio")
    }

    override fun visit(videoFile: VideoFile) {
        println("No es un Audio")
    }

    override fun visit(audioFile: AudioFile) {
        println("El audio del artist ${audioFile.audioFile.artist} dura ${audioFile.audioFile.size * 60} segundos")
    }
}

fun main() {
    val files = listOf(
        ImageFile(MediaFileDummy.Image(640, 480)),
        VideoFile(MediaFileDummy.Video(30, 600000)),
        AudioFile(MediaFileDummy.Audio("Jane Doe", 200000))
    )

    val resolutionVisitor = ResolutionVisitor()
    val durationVisitor = DurationVisitor()
    val sizeVisitor = SizeVisitor()

    files.forEach { file ->
        println("-------------- Visitante de resolucion --------------")
        file.accept(resolutionVisitor)
        println("-------------- Visitante de duracion --------------")
        file.accept(durationVisitor)
        println("-------------- Visitante de peso --------------")
        file.accept(sizeVisitor)
    }
}
