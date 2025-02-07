package behaviour.command

interface TaskCommand {
    fun execute()
    fun undo()
}

/**
 * Clases pricipales
 */

class UserManager(private val name: String) {
    fun create() {
        println("El usuario $name fue creado.")
    }

    fun delete() {
        println("El usuario $name fue eliminado.")
    }

    fun restore() {
        println("El usuario $name ha sido restaurado.")
    }
}

class EmailManager {
    fun cancel() {
        println("Cancelado el envio masivo de emails.")
    }

    fun sendMassEmail() {
        println("Enviando correo electronico masivo.")
    }
}

class ReportManager {
    fun generate() {
        println("El reporte a sido generado correctamente.")
    }

    fun delete() {
        println("El reporte ha sido eliminado.")
    }
}


/**
 *
 * Comandos que ejecutan las acciones
 *
 */

class CreateUserCommand(private val userManager: UserManager): TaskCommand {
    override fun execute() {
        userManager.create()
    }

    override fun undo() {
        userManager.delete()
    }
}

class DeleteUserCommand(private val userManager: UserManager): TaskCommand {
    override fun execute() {
        userManager.delete()
    }

    override fun undo() {
        userManager.restore()
    }
}

class SendMassEmailCommand(private val emailManager: EmailManager): TaskCommand {
    override fun execute() {
        emailManager.sendMassEmail()
    }

    override fun undo() {
        emailManager.cancel()
    }
}

class GenerateReportCommand(private val reportManager: ReportManager): TaskCommand {
    override fun execute() {
        reportManager.generate()
    }

    override fun undo() {
        reportManager.delete()
    }
}

/**
 * Manejador de comandos
 */
class TaskManager {
    private val commands = mutableMapOf<Int, TaskCommand>()
    private val history = mutableListOf<Int>()

    fun addCommand(action: Int, command: TaskCommand) {
        commands[action] = command
    }

    fun trigger(action: Int) {
        if (commands.containsKey(action)) {
            commands[action]?.execute()
            history.add(action)
            return
        }
        println("El comando no existe.")
    }

    fun revert() {
        val lastAction = history.lastOrNull() ?: return
        commands[lastAction]?.undo()
        history.removeLast()
        println("La accion $lastAction fue removida.")
    }

    fun showHistory() {
        println("Historial de acciones: ${history.joinToString(" -> ")}")
    }
}

fun main() {

    val taskManager = TaskManager()

    val userManager = UserManager("David")
    val emailManager = EmailManager()
    val reportManager = ReportManager()

    taskManager.addCommand(1, CreateUserCommand(userManager))
    taskManager.addCommand(2, DeleteUserCommand(userManager))
    taskManager.addCommand(3, SendMassEmailCommand(emailManager))
    taskManager.addCommand(4, GenerateReportCommand(reportManager))

    println("""
        Menu de comandos:
        1. Crear usuario
        2. Eliminar usuario
        3. Enviar correos masivos
        4. Generar reporte
    """.trimIndent())

    println("----------------------------------------------------------------")

    taskManager.trigger(1)
    taskManager.trigger(2)
    taskManager.trigger(3)
    taskManager.trigger(4)
    taskManager.trigger(3)
    taskManager.trigger(1)
    println("----------------------------------------------------------------")

    taskManager.showHistory()

    taskManager.revert()
    taskManager.revert()
    taskManager.revert()

    taskManager.showHistory()
}
