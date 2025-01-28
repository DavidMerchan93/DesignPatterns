package structurals.bridge

/**
 * Desacopla una abstraccion de su implementacion para que las 2 puedan cambiar de manera independiente.
 * Uso extensivo de la composición.
 *
 * 	Desacoplamiento:
 * 	Los personajes no dependen directamente de una implementación específica de ataque.
 * 	El ataque se inyecta como un objeto de tipo Attack. Esto permite que las clases Warrior y Wizard permanezcan inmutables
 * 	incluso si se agregan nuevos tipos de ataque.
 *
 * 	Extensibilidad:
 * 	Puedes agregar nuevos tipos de ataques o nuevos personajes sin que uno afecte al otro. Por ejemplo, si mañana decides agregar
 * 	un MagicAttack o un Archer, no necesitas modificar las clases existentes, simplemente usas
 * 	o extiendes las abstracciones existentes.
 *
 * 	Reutilización: La lógica común para realizar un ataque está en la clase base Character, lo que elimina duplicación y
 * 	mejora la mantenibilidad. Los detalles específicos de cómo se realiza el ataque están en las clases concretas de Attack.
 */

interface Attack {
    fun attack()
}

class SwordAttack : Attack {
    override fun attack() {
        println("Ataque con espada grande")
    }
}


class BowAttack : Attack {
    override fun attack() {
        println("Ataque con arco ligero")
    }
}

class AxeAttack : Attack {
    override fun attack() {
        println("Ataque con hacha fuerte")
    }
}

abstract class Character(private val attack: Attack) {

    abstract var name: String

    fun performAttack() {
        println(name)
        attack.attack()
    }
}

class Warrior(attack: Attack, override var name: String) : Character(attack)
class Wizard(attack: Attack, override var name: String) : Character(attack)

fun main() {
    val warrior = Warrior(SwordAttack(),"Juan")
    warrior.performAttack()

    val wizard = Wizard(BowAttack(),"Maria")
    wizard.performAttack()

    val warriorWithBow = Warrior(BowAttack(), "Legolas")
    warriorWithBow.performAttack()

    val drawn = Warrior(AxeAttack(), "Gimbli")
    drawn.performAttack()
}