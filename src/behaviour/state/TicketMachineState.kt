package behaviour.state

enum class TicketType {
    GOLD,
    PLATINUM,
    DEFAULT,
}

data class Ticket(
    var amount: Int = 0,
    var type: TicketType = TicketType.DEFAULT,
    var date: String = ""
)

/**
 * Interface defining the behavior of a state in the ticket machine.
 *
 * This interface outlines the methods that must be implemented by each state in the state machine.
 * The methods represent the possible actions that can be performed in each state.
 */
interface MachineState {
    fun selectTicket(type: TicketType)
    fun insertMoney(amount: Int)
    fun selectDate(date: String)
    fun dispenseTicket()
}

/**
 * Represents a ticket machine that manages the state of ticket selection, money insertion, date selection,
 * and ticket dispensing based on a state machine pattern.
 *
 * The [TicketMachine] class maintains the current state of the machine and delegates actions to the
 * appropriate state object. The state objects implement the [MachineState] interface, which defines the
 * behavior for each state in the state machine.
 *
 * The [TicketMachine] class has the following properties:
 * - [ticket]: A [Ticket] object representing the current ticket being processed.
 * - [state]: A [MachineState] object representing the current state of the machine.
 */
class TicketMachine {

    var ticket: Ticket = Ticket()
    private var state: MachineState

    init {
        state = SelectTicketState(this)
    }

    fun selectTicket(type: TicketType) {
        state.selectTicket(type)

    }

    fun insertMoney(amount: Int) {
        state.insertMoney(amount)
    }

    fun selectDate(date: String) {
        state.selectDate(date)
    }

    fun dispenseTicket() {
        state.dispenseTicket()
    }

    fun transitToState(state: MachineState) {
        this.state = state
    }
}

/**
 * Represents a state in the ticket machine where the user has selected a ticket type.
 *
 * This class implements the [MachineState] interface and defines the behavior for the 'SelectTicketState'.
 * In this state, the user can select a ticket type, but they cannot insert money, select a date, or dispense a ticket.
 *
 */
class SelectTicketState(
    private var machineState: TicketMachine
) : MachineState {
    override fun selectTicket(type: TicketType) {
        machineState.ticket.type = type
        println("Ticket selccionado")
        machineState.transitToState(MoneyInsertedState(machineState))
    }

    override fun insertMoney(amount: Int) {
        println("No se puede insertar el dinero sin seleccionar el tipo de ticket")
    }

    override fun selectDate(date: String) {
        println("No se puede seleccionar la fecha sin seleccionar el tipo de ticket")
    }

    override fun dispenseTicket() {
        println("No se puede dar el ticket sin realizar todo el proceso")
    }

}

/**
 * Represents a state in the ticket machine where the user has inserted money.
 *
 * This class implements the [MachineState] interface and defines the behavior for the 'MoneyInsertedState'.
 * In this state, the user can insert money, but they cannot select a ticket type or date, or dispense a ticket.
 */
class MoneyInsertedState(
    private var machineState: TicketMachine
) : MachineState {
    override fun selectTicket(type: TicketType) {
        println("Ya se selecciono el tipo de ticket")
    }

    override fun insertMoney(amount: Int) {
        machineState.ticket.amount = amount
        println("Monto del ticket insertado")
        machineState.transitToState(SelectDateState(machineState))
    }

    override fun selectDate(date: String) {
        println("No se puede seleccionar la fecha sin insertar el monto del ticket")
    }

    override fun dispenseTicket() {
        println("No se puede dar el ticket sin realizar todo el proceso")
    }
}

/**
 * Represents a state in the ticket machine where the user has selected a date for the ticket.
 *
 * This class implements the [MachineState] interface and defines the behavior for the 'SelectDateState'.
 * In this state, the user can select a date, but they cannot select a ticket type or insert money, or dispense a ticket.
 */
class SelectDateState(
    private var machineState: TicketMachine
) : MachineState {
    override fun selectTicket(type: TicketType) {
        println("Ya se selecciono el tipo de ticket")
    }

    override fun insertMoney(amount: Int) {
        println("Ya ingreso el monto del ticket")
    }

    override fun selectDate(date: String) {
        machineState.ticket.date = date
        println("Fecha del ticket seleccionada")
        machineState.transitToState(DispenseTicketState(machineState))
    }

    override fun dispenseTicket() {
        println("No se puede dar el ticket sin realizar todo el proceso")
    }
}

/**
 * Represents a state in the ticket machine where the user has selected a date for the ticket.
 *
 * This class implements the [MachineState] interface and defines the behavior for the 'SelectDateState'.
 * In this state, the user can select a date, but they cannot select a ticket type or insert money, or dispense a ticket.
 */
class DispenseTicketState(
    private var machineState: TicketMachine
) : MachineState {
    override fun selectTicket(type: TicketType) {
        println("Ya se selecciono el tipo de ticket")
    }

    override fun insertMoney(amount: Int) {
        println("Ya ingreso el monto del ticket")
    }

    override fun selectDate(date: String) {
        println("Ya la fecha del ticket")
    }

    override fun dispenseTicket() {
        println(
            """
            Ticket emitido:
            - Monto: ${machineState.ticket.amount}
            - Tipo: ${machineState.ticket.type}
            - Fecha: ${machineState.ticket.date}
        """.trimIndent()
        )
        machineState.transitToState(SelectTicketState(machineState))
    }
}

fun main() {

    // Happy path
    TicketMachine().apply {
        selectTicket(TicketType.PLATINUM)
        insertMoney(1000)
        selectDate("2022-12-31")
        dispenseTicket()
    }
    println("------------------------------------------------")
    // Error paths
    TicketMachine().apply {
        insertMoney(1000)
        selectTicket(TicketType.GOLD)
        insertMoney(1000)
        dispenseTicket()
        selectDate("2022-12-31")
        selectTicket(TicketType.PLATINUM)
        dispenseTicket()
    }
}
