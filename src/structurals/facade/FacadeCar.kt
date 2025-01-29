package structurals.facade

/**
 * El patrón Facade es un patrón estructural que proporciona una interfaz simplificada
 * para un conjunto complejo de clases, bibliotecas o sistemas. En lugar de interactuar
 * directamente con los numerosos componentes subyacentes, el cliente usa la fachada como
 * un único punto de entrada.
 */

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val cartQuantity: Int,
    var stockQuantity: Int,
)

class ValidateShoppingCart(
    private val products: MutableList<Product>
) {
    fun checkCart() {
        println("Check shopping cart products")
        products.forEach {
            println("${it.id} - ${it.name} - ${it.price}")
        }
    }

    fun clearCart() {
        println("Cleaning shopping cart products")
        products.clear()
    }
}

class ProcessPayment {
    fun openConnection() {
        println("Openning database connection")
    }

    fun processPayment() {
        println("Processing payment...")
    }

    fun closeConnection() {
        println("Closing database connection")
    }
}

class GenerateOrder {
    fun generateOrder() {
        println("Generating order...")
    }
}

class SendUserNotification {
    fun startConnectionService() {
        println("Starting connection service email...")
    }

    fun sendNotification() {
        println("Sending user email...")
    }

    fun stopConnectionService() {
        println("Stopping connection service email...")
    }
}

class UpdateProductsStock(
    private val products: MutableList<Product>
) {
    fun updateProductStock() {
        println("Updating products stock...")
        products.forEach { product ->
            product.stockQuantity -= product.cartQuantity
            if (product.stockQuantity < product.cartQuantity) {
                println("Warning: Low stock for product ${product.id} - ${product.name}")
            }
        }
    }
}

// La fachada agrupda todas las implementacion y las oculta de una implementacion
class ShoppingCartFacade(
    private val products: MutableList<Product>
) {

    private val validateShoppingCart = ValidateShoppingCart(products)
    private val processPayment = ProcessPayment()
    private val generateOrder = GenerateOrder()
    private val sendUserNotification = SendUserNotification()
    private val updateProductsStock = UpdateProductsStock(products)

    fun starShoppingProcess() {
        // Check cart products
        validateShoppingCart.checkCart()

        // Process payment
        processPayment.openConnection()
        processPayment.processPayment()

        // Generate order
        generateOrder.generateOrder()

        // Send user notification
        sendUserNotification.startConnectionService()
        sendUserNotification.sendNotification()
    }

    fun endShoppingProcess() {

        // Close connection payments
        processPayment.closeConnection()

        // Close connection notifications
        sendUserNotification.stopConnectionService()

        // Update product stock
        updateProductsStock.updateProductStock()

        // Clear shopping cart products
        validateShoppingCart.clearCart()
    }
}

fun main() {

    val products = mutableListOf(
        Product(1, "Product A", 10.0, 5, 10),
        Product(2, "Product B", 20.0, 3, 15),
        Product(3, "Product C", 30.0, 2, 20)
    )

    val shoppingCartFacade = ShoppingCartFacade(products)

    shoppingCartFacade.starShoppingProcess()

    shoppingCartFacade.endShoppingProcess()

}
