package behaviour.observer


/**
 * This interface defines the behavior of an observer that will be notified about changes in temperature.
 */
interface Observer {
    val name: String
    fun notify(temperature: Int)
}

/**
 * This class represents a temperature monitor that notifies observers about changes in temperature.
 * It maintains a list of observers and provides methods to subscribe and unsubscribe them.
 * When the temperature is updated, it notifies all subscribed observers.
 */
class TemperatureMonitor {

    private val observers = mutableListOf<Observer>()

    fun subscribe(observer: Observer) {
        println("${observer.name} se a conectado")
        observers.add(observer)
    }

    fun unsubscribe(observer: Observer) {
        println("${observer.name} se a desconectado")
        observers.remove(observer)
    }

    fun updateTemperature(temperature: Int) {
        println("Se a notificado a los observadores la temperatura $temperature ªC")
        observers.forEach {
            it.notify(temperature)
        }
    }
}

/**
 * This class represents a heating controller that is an observer in the observer design pattern.
 * It is responsible for adjusting the temperature of the greenhouse based on the received temperature notifications.
 *
 * @param name The name of the heating controller. Default value is "HeatingController".
 */
class HeatingController(override val name: String = "HeatingController") : Observer {

    override fun notify(temperature: Int) {
        if (temperature < 10) {
            println("-- Temperatura baja < 10 ºC - Ajustado la temperatura del invernadero.")
        }
    }
}

/**
 * This class represents a control panel that is an observer in the observer design pattern.
 * It is responsible for handling temperature alerts based on the received temperature notifications.
 *
 * @param name The name of the control panel. Default value is "ControlPanel".
 */
class ControlPanel(override val name: String = "ControlPanel") : Observer {
    override fun notify(temperature: Int) {
        if (temperature <= 5) {
            println("--- Alerta!! La temperatura es demaciado baja <= 5 ºC")
        }
        if (temperature >= 20) {
            println("---- Alerta!! La temperatura es demasiado alta >= 20 ªC")
        }
    }
}

/**
 * This class represents a ventilation system that is an observer in the observer design pattern.
 * It is responsible for adjusting the ventilation based on the received temperature notifications.
 *
 * @param name The name of the ventilation system. Default value is "VentilationSystem".
 */
class VentilationSystem(override val name: String = "VentilationSystem") : Observer {
    override fun notify(temperature: Int) {
        if (temperature >= 25) {
            println("- Activando los ventiladores dada las altas temperaturas >= 25 ºC")
        }
    }
}


/**
 * This class represents a display that is an observer in the observer design pattern.
 * It is responsible for displaying the current temperature.
 */
class Display(override val name: String = "Display") : Observer {
    override fun notify(temperature: Int) {
        println("-------- Temperatura actual $temperature ºC --------")
    }
}

fun main() {
    val heatingController = HeatingController()
    val controlPanel = ControlPanel()
    val ventilationSystem = VentilationSystem()
    val display = Display()

    val temperatureMonitor = TemperatureMonitor()

    temperatureMonitor.subscribe(heatingController)
    temperatureMonitor.subscribe(controlPanel)
    temperatureMonitor.subscribe(ventilationSystem)
    temperatureMonitor.subscribe(display)

    temperatureMonitor.updateTemperature(15)
    temperatureMonitor.updateTemperature(22)
    temperatureMonitor.updateTemperature(28)
    temperatureMonitor.updateTemperature(12)
    temperatureMonitor.updateTemperature(8)
    temperatureMonitor.updateTemperature(2)

    temperatureMonitor.unsubscribe(heatingController)
    temperatureMonitor.updateTemperature(1) // No se activa el sistema de calefaccion

}