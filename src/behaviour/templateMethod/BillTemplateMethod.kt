package behaviour.templateMethod

import java.util.*

typealias TotalAndTax = Pair<Double, Double>

data class Products(
    val price: Double,
    val detail: String,
    val quantity: Int
)

data class Invoice(
    var date: String = "",
    var subtotal: Double = .0,
    var total: Double = .0,
    var tax: Double = .0
)

/**
 * Abstract class for generating invoices with different tax rules.
 *
 * @param products A list of products to be included in the invoice.
 *
 * @property invoice An instance of the Invoice data class to store invoice details.
 *
 * @constructor Initializes the InvoiceGenerator with the provided products.
 *
 * @see Invoice
 */
abstract class InvoiceGenerator(
    private val products: MutableList<Products>
) {
    private var invoice = Invoice()

    fun generateInvoice() {
        // Validar los datos
        if (validateData().not()) return

        // Calcular el total
        val calculatedInvoice = calculateTotal(invoice.subtotal)
        invoice.apply {
            total = calculatedInvoice.first
            tax = calculatedInvoice.second
        }

        // Formatear lectura
        formatInvoice()

        // Exportar PDF
        exportPdf()
    }

    private fun validateData(): Boolean {
        if (products.isNotEmpty()) {
            println("Productos validados con éxito")
            invoice = invoice.copy(
                subtotal = products.sumOf {
                    it.price * it.quantity
                }
            )
        } else {
            println("Error al validar los productos")
        }

        return products.isNotEmpty()
    }

    private fun formatInvoice() {
        invoice = invoice.copy(
            date = getCurrentDate()
        )
        println("Factura formateada con éxito.")
    }

    private fun exportPdf() {
        println(
            """
          -------------------------------- Imprimiendo factura --------------------------------
            |Factura
            |Fecha: ${invoice.date}
            |Subtotal: ${invoice.subtotal}
            |IVA: ${invoice.tax}
            |Total: ${invoice.total}
            |
            |# Productos: ${products.size}
            |
        """.trimIndent()
        )
    }

    protected abstract fun calculateTotal(subtotal: Double): TotalAndTax

    private fun getCurrentDate(): String {
        return Date().toString()
    }
}

/**
 * A class representing a standard invoice generator that calculates the total with a standard VAT rate.
 *
 * @param products A list of products to be included in the invoice.
 *
 * @property iva The standard VAT rate for standard invoices.
 *
 * @constructor Initializes the StandardInvoiceGenerator with the provided products.
 *
 * @see InvoiceGenerator
 */
// Una factura regular necesita agregar un IVA estándar.
class StandardInvoiceGenerator(products: MutableList<Products>) : InvoiceGenerator(products) {
    private val iva = 0.19

    init {
        println("Generando factura Standard")
    }

    override fun calculateTotal(subtotal: Double): TotalAndTax {
        val total = subtotal * (iva + 1.0)
        println("El iva aplicado es de $iva del subtotal")
        return Pair(total, iva)
    }
}

/**
 * A class representing an exempt invoice generator that calculates the total without any taxes.
 *
 * @param products A list of products to be included in the invoice.
 *
 * @constructor Initializes the ExemptInvoiceGenerator with the provided products.
 *
 * @see InvoiceGenerator
 */
// Una factura exenta no aplica impuestos.
class ExemptInvoiceGenerator(products: MutableList<Products>) : InvoiceGenerator(products) {

    init {
        println("Generando factura Exenta de impuestos")
    }

    override fun calculateTotal(subtotal: Double): TotalAndTax {
        println("No aplica iva o impuestos")
        return Pair(subtotal, .0)
    }
}

/**
 * A class representing an international invoice generator that calculates the total with a special tax rate.
 *
 * @param products A list of products to be included in the invoice.
 *
 * @property ivaInternational The special tax rate for international invoices.
 *
 * @constructor Initializes the InternationalInvoiceGenerator with the provided products.
 *
 * @see InvoiceGenerator
 */
// Una factura internacional aplica un cálculo de impuestos especial.
class InternationalInvoiceGenerator(products: MutableList<Products>) : InvoiceGenerator(products) {
    private val ivaInternational = 0.25

    init {
        println("Generando factura Internacional")
    }

    override fun calculateTotal(subtotal: Double): TotalAndTax {
        val total = subtotal * (ivaInternational + 1.0)
        println("El iva aplicado es de $ivaInternational del subtotal")
        return Pair(total, ivaInternational)
    }
}

fun main() {

    val products = mutableListOf(
        Products(10.0, "Producto A", 4),
        Products(20.0, "Producto B", 5),
        Products(30.0, "Producto C", 2)
    )

    println("--------------------------------------------")
    val standardInvoiceGenerator = StandardInvoiceGenerator(products)
    standardInvoiceGenerator.generateInvoice()
    println("--------------------------------")
    val exemptInvoiceGenerator = ExemptInvoiceGenerator(products)
    exemptInvoiceGenerator.generateInvoice()
    println("--------------------------------")
    val internationalInvoiceGenerator = InternationalInvoiceGenerator(products)
    internationalInvoiceGenerator.generateInvoice()
}

