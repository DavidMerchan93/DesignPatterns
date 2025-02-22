package behaviour.strategy

enum class Category {
    ELECTRONIC,
    FOOD,
}

data class Product(
    val name: String,
    var price: Double,
    val category: Category
)

/**
 * This interface define the strategy to be implemented, in this case depend of the shopping cart
 */
interface PromotionStrategy {
    fun applyPromotion(products: List<Product>): Double
}

/**
 * A strategy for applying a promotion based on the total amount of products in the shopping cart.
 * If the total amount is greater than or equal to 100, a 10% discount is applied.
 */
class TotalAmountStrategy : PromotionStrategy {
    override fun applyPromotion(products: List<Product>): Double {
        var subtotal = .0
        products.forEach {
            subtotal += it.price
        }
        if (subtotal >= 100) {
            return subtotal * 0.9
        }
        return subtotal
    }
}

/**
 * A strategy for applying a promotion based on the category of products in the shopping cart.
 * This strategy applies a 20% discount to electronic products and leaves the price unchanged for food products.
 */
class ElectronicCategoryStrategy : PromotionStrategy {
    override fun applyPromotion(products: List<Product>): Double {
        var subtotal = .0
        products.forEach {
            subtotal += if (it.category == Category.ELECTRONIC) {
                it.price * 0.8
            } else {
                it.price
            }
        }
        return subtotal
    }
}

/**
 * A strategy for applying a promotion based on the season.
 * This strategy applies a 15% discount to the total amount of products in the shopping cart.
 */
class WinterSeasonStrategy : PromotionStrategy {
    override fun applyPromotion(products: List<Product>): Double {
        var subtotal = .0
        products.forEach {
            subtotal += it.price
        }
        return subtotal * 0.85
    }
}

/**
 * A class representing a shopping cart that applies a promotion strategy based on the selected strategy.
 *
 * @param discountStrategy The initial promotion strategy to be applied.
 */
class ShoppingCart(
    private var discountStrategy: PromotionStrategy
) {

    private val cart = mutableListOf<Product>()

    fun addProduct(product: Product) {
        cart.add(product)
    }

    fun removeProduct(product: Product) {
        cart.remove(product)
    }

    /**
     * Applies the discount strategy to the products in the shopping cart and prints the results.
     *
     * This function calculates the subtotal of the products in the shopping cart, applies the current
     * discount strategy to the subtotal, and prints the applied discount strategy, subtotal, and total
     * (with or without discount).
     *
     * @return Nothing. This function prints the results and does not return any value.
     */
    fun applyDiscount() {
        var subtotal = .0
        cart.forEach { subtotal += it.price }

        val total = discountStrategy.applyPromotion(cart)

        println("Aplicando descuento: ${discountStrategy.javaClass.simpleName}")
        println("Subtotal: $ $subtotal")
        if (subtotal != total) {
            println("Total con descuento: $ $total")
        } else {
            println("El total sin descuento es: $ $subtotal")
        }
    }

    /**
     * Changes the current promotion strategy applied to the shopping cart.
     *
     * @param strategy The new promotion strategy to be applied. It should implement the [PromotionStrategy] interface.
     *
     * @return Nothing. This function changes the internal state of the shopping cart.
     */
    fun changeDiscount(strategy: PromotionStrategy) {
        this.discountStrategy = strategy
        println("Se cambia la estrategia por: ${strategy.javaClass.simpleName}")
    }
}

fun main() {
    val products = listOf(
        Product("Monitor", 150.0, Category.ELECTRONIC),
        Product("Teclado", 80.0, Category.ELECTRONIC),
        Product("Pan", 40.0, Category.FOOD),
        Product("Leche", 45.0, Category.FOOD),
    )

    val shoppingCart = ShoppingCart(ElectronicCategoryStrategy())
    products.forEach { shoppingCart.addProduct(it) }

    shoppingCart.applyDiscount()

    println("--------- Cambiamos a invierno ---------")
    shoppingCart.changeDiscount(WinterSeasonStrategy())
    shoppingCart.applyDiscount()

    println("--------- Cambiamos a descuento total ---------")
    shoppingCart.changeDiscount(TotalAmountStrategy())
    shoppingCart.applyDiscount()

    shoppingCart.removeProduct(products[0])
    shoppingCart.removeProduct(products[1])
    shoppingCart.applyDiscount()
}
