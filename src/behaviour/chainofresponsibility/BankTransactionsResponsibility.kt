package behaviour.chainofresponsibility

enum class TransactionStatus {
    IN_PROGRESS,
    PENDING,
    VALIDATED,
    APPROVED,
}

enum class UserType {
    ADMIN,
    USER
}

data class Transaction(
    val numberCount: Int,
    val amount: Int,
    val status: TransactionStatus,
    val userType: UserType
)

interface TransactionHandler {
    fun setNext(handler: TransactionHandler): TransactionHandler
    fun handle(transaction: Transaction): Boolean
}

abstract class TransactionProcess : TransactionHandler {
    var nextHandler: TransactionHandler? = null

    override fun setNext(handler: TransactionHandler): TransactionHandler {
        nextHandler = handler
        return handler
    }

    override fun handle(transaction: Transaction): Boolean {
        return nextHandler?.handle(transaction) ?: false
    }
}


class ValidationHandler : TransactionProcess() {
    override fun handle(transaction: Transaction): Boolean {
        if (transaction.amount > 0 && transaction.numberCount != 0) {
            println("El monto y numero de cuenta es correcto")
            return nextHandler?.handle(transaction.copy(status = TransactionStatus.PENDING)) ?: true
        }
        println("El monto y el numero de cuenta son incorrectos")
        return false
    }
}

class FraudDetectionHandler : TransactionProcess() {
    override fun handle(transaction: Transaction): Boolean {
        if (transaction.amount < 100000) {
            println("El monto esta dentro de los parametros permitidos")
            return nextHandler?.handle(transaction.copy(status = TransactionStatus.VALIDATED)) ?: true
        }
        println("Posible caso de fraude monto muy elevado")
        return false
    }
}

class AuthorizationHandler : TransactionProcess() {
    override fun handle(transaction: Transaction): Boolean {
        if (transaction.status == TransactionStatus.VALIDATED && transaction.userType == UserType.ADMIN) {
            println("La transaccion cuenta con los permisos requeridos")
            return nextHandler?.handle(transaction.copy(status = TransactionStatus.APPROVED)) ?: true
        }
        println("Transaccion no aprovada, no cuenta con los permisos necesarios.")
        return false
    }
}

class ApprovalHandler : TransactionProcess() {
    override fun handle(transaction: Transaction): Boolean {
        if (transaction.status == TransactionStatus.APPROVED) {
            println("Transaccion aprobada, realizando pago.")
            return true
        }

        println("Transaccion no aprovada, no se realizo el pago.")
        return false
    }
}

fun main() {

    val transaction = Transaction(
        numberCount = 1234567890,
        amount = 50000,
        status = TransactionStatus.IN_PROGRESS,
        userType = UserType.USER
    )

    val validationHandler = ValidationHandler()
    val fraudDetectionHandler = FraudDetectionHandler()
    val authorizationHandler = AuthorizationHandler()
    val approvalHandler = ApprovalHandler()

    // Esta es nuestra cadena de responsabilidades
    validationHandler
        .setNext(fraudDetectionHandler)
        .setNext(authorizationHandler)
        .setNext(approvalHandler)

    validationHandler.handle(transaction)
}
