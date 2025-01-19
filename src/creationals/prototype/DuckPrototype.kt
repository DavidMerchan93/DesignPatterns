package creationals.prototype

interface DuckPrototype {
    fun clone(): DuckPrototype
}

data class SimpleDuck(
    var name: String,
    var color: String
): DuckPrototype {
    override fun clone(): DuckPrototype {
        return this.copy()
    }
}

data class SpecialDuck(
    var name: String,
    var color: String,
    var weapon: String
): DuckPrototype {
    override fun clone(): DuckPrototype {
        return this.copy()
    }
}

fun main() {

    val simpleDuckPrototype = SimpleDuck(name = "Pedro", color = "Rojo")
    val specialDuckPrototype = SpecialDuck(name = "Juan", color = "Azul", weapon = "Granadas")

    println(simpleDuckPrototype)
    println(specialDuckPrototype)

    val otherSimpleDuck = simpleDuckPrototype.clone() as SimpleDuck
    otherSimpleDuck.name = "Maria"

    val otherSpecialDuck = specialDuckPrototype.clone() as SpecialDuck
    otherSpecialDuck.name = "Juanita"

    println(otherSimpleDuck)
    println(otherSpecialDuck)
}