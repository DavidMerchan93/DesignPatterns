package structurals.adapter

// Creamos un logger en file
class FileLogger {
    fun log(message: String) {
        println("Writing to file: $message")
    }
}

// Tambien creamos un logger en remote simulando que es de terceros
class RemoteLogger {
    fun logEvent(message: String) {
        println("Remote:")
        println("Sending to remote server: $message")
    }
}

// Creamos una interface que va hacer de adapter
interface LoggerAdapter {
    fun log(message: String)
}

class FileLog : LoggerAdapter {
    private val logger = FileLogger()

    override fun log(message: String) {
        logger.log(message)
    }
}

class RemoteLog : LoggerAdapter {
    private val logger = RemoteLogger()

    override fun log(message: String) {
        logger.logEvent(message)
    }
}

// Podrimos tener muchos archivos que usen FileLogger
// pero si llegamos a cambiar de logger, tendremos que
// hacerlo en todos los archivos
class Repositoru(private val logger: LoggerAdapter) {
    fun save(message: String) {
        logger.log(message)
    }
}

fun main() {
    // Usamos la implementacion de file
    var logger: LoggerAdapter = FileLog()

    // Ahora inyectamos la implementacion en el repositorio
    var repository = Repositoru(logger)
    repository.save("Hello from File Logger")

    // ahora si cambiamos de logger esto no afecta el funcionamiento de nuestro repo
    logger = RemoteLog()
    repository = Repositoru(logger)
    repository.save("Hello from Remote Logger")
}