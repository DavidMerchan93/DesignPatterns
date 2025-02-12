package behaviour.mediator

enum class FormEvents {
    SELECT_COUNTRY,
    UNSELECT_COUNTRY,
    SELECT_PAYMENT_METHOD,
    UNSELECT_PAYMENT_METHOD,
    VERIFICATION_OK,
    VERIFICATION_ERROR,
}

interface FormMediator {
    fun notify(sender: FormComponent, event: FormEvents)
}

interface FormComponent {
    val name: String
    val mediator: FormMediator

    fun triggerEvent()

    fun setEnable(isEnabled: Boolean) {
        val status = if (isEnabled) {
            "Habilitado"
        } else {
            "Desabilitado"
        }
        println("$name ahora esta $status")
    }

    fun setVisibility(isVisible: Boolean) {
        val status = if (isVisible) {
            "Visible"
        } else {
            "Oculto"
        }
        println("$name ahora esta $status")
    }

    fun setVerification(isOk: Boolean) {
        val status = if (isOk) {
            "Verificado"
        } else {
            "No verificado"
        }
        println("$name ahora esta $status")
    }
}

class Mediator : FormMediator {

    private val components = mutableListOf<FormComponent>()

    fun addComponent(component: FormComponent) {
        components.add(component)
    }

    override fun notify(sender: FormComponent, event: FormEvents) {
        components.filter { it != sender }.forEach { component ->
            when (event) {
                FormEvents.SELECT_COUNTRY -> component.setEnable(false)
                FormEvents.UNSELECT_COUNTRY -> component.setEnable(true)
                FormEvents.SELECT_PAYMENT_METHOD -> component.setVisibility(false)
                FormEvents.UNSELECT_PAYMENT_METHOD -> component.setVisibility(true)
                FormEvents.VERIFICATION_OK -> component.setVerification(true)
                FormEvents.VERIFICATION_ERROR -> component.setVerification(false)
            }
        }
    }
}

class CheckBox(
    override val name: String,
    override val mediator: FormMediator
) : FormComponent {

    private var isCheck = false

    override fun triggerEvent() {
        val event = if (isCheck) {
            FormEvents.UNSELECT_COUNTRY
        } else {
            FormEvents.SELECT_COUNTRY
        }

        println("$name envia el evento $event y isCheck es $isCheck")
        mediator.notify(this, event)
        isCheck = !isCheck
    }
}

class TextField(
    override val name: String,
    override val mediator: FormMediator
) : FormComponent {

    private var isVerified = false

    override fun triggerEvent() {
        val event = if (isVerified) {
            FormEvents.UNSELECT_COUNTRY
        } else {
            FormEvents.SELECT_COUNTRY
        }

        println("$name envia el evento $event y isVerified es $isVerified")
        mediator.notify(this, event)
        isVerified = !isVerified
    }
}

class SelectBox(
    override val name: String,
    override val mediator: FormMediator
) : FormComponent {

    private var isSelectedPayment = false

    override fun triggerEvent() {
        val event = if (isSelectedPayment) {
            FormEvents.SELECT_PAYMENT_METHOD
        } else {
            FormEvents.UNSELECT_PAYMENT_METHOD
        }

        println("$name envia el evento $event y isSelectedPayment es $isSelectedPayment")
        mediator.notify(this, event)
        isSelectedPayment = !isSelectedPayment
    }
}

fun main() {
    // Creamos el mediador
    val mediator = Mediator()

    // Creamos los componentes del formulario
    val checkBox = CheckBox("Checkbox", mediator)
    val textField = TextField("TextField", mediator)
    val selectBox = SelectBox("SelectBox", mediator)

    // Agregamos los componentes al mediador
    mediator.addComponent(checkBox)
    mediator.addComponent(textField)
    mediator.addComponent(selectBox)

    // Simulamos los eventos del formulario
    checkBox.triggerEvent()
    println("----------------------------------------------------------------")
    textField.triggerEvent()
    println("----------------------------------------------------------------")
    selectBox.triggerEvent()
    println("----------------------------------------------------------------")
    textField.triggerEvent()
    println("----------------------------------------------------------------")
    selectBox.triggerEvent()
    println("----------------------------------------------------------------")
    checkBox.triggerEvent()
}

