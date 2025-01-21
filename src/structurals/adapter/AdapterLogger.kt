package structurals.adapter

// Creamos una interface que va hacer de adapter
interface MyLogger {
    fun log(message: String)
}

// Ahora creamos una implementacion
class FileLogger: MyLogger {
    override fun log(message: String) {
        println("Writing to file: $message")
    }
}

class RemoteLogger: MyLogger {
    override fun log(message: String) {
        println("Remote:")
        println("Sending to remote server: $message")
    }
}

class Repositoru(val logger: MyLogger) {
    fun save(message: String) {
        logger.log(message)
    }
}

fun main() {
    // Usamos la implementacion de file
    var logger: MyLogger= FileLogger()

    // Ahora inyectamos la implementacion en el repositorio
    var repository = Repositoru(logger)
    repository.save("Hello from File Logger")

    // ahora si cambiamos de logger esto no afecta el funcionamiento de nuestro repo
    logger = RemoteLogger()
    repository = Repositoru(logger)
    repository.save("Hello from Remote Logger")
}