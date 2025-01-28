package structurals.decorator

/**
 * Permite añadir funcionalidades a objetos y estos objetos colocarlos en
 * `objetos encapsuladores` que contienen esas funcionalidad
 *
 * La idea es que en tiempo de ejecucion podamos ir agregando
 * funcionalidades a cada objeto.
 */

// Declaramos una interfaz de nuestro objetos que va a ser decorado
interface Character {
    fun perfomAttack()
}

// Ahora creamos un objeto base, que sera el que nos sirva para ir decorando
class BasicCharacter : Character {
    override fun perfomAttack() {
        println("El personaje ataca con sus puños.")
    }
}


// Ahora creamos una clase abstracta, que nos permitira crear los decoradores, con base
// En el objeto base que le pasemos
abstract class CharacterDecorator(
    private val character: Character
): Character {
    override fun perfomAttack() {
        character.perfomAttack()
    }
}

// Creamos un decorador que se compone de nuestro decorador y que llama el funcionamiento
// de nuestro elemento base mas el que agregamos
class KickCharacter(
    private val character: Character
): CharacterDecorator(character) {
    override fun perfomAttack() {
        super.perfomAttack()
        println("El personaje ahora ataca con una patada alta")
    }
}

class HeadCharacter(
    private val character: Character
): CharacterDecorator(character) {
    override fun perfomAttack() {
        super.perfomAttack()
        println("El personaje ahora da un cabezaso")
    }
}

// Por ultimo podemos componer nuestro objeto en tiempo de ejecucion
// con los diferentes decoradores
fun main() {
    var character: Character = BasicCharacter()
    character = KickCharacter(character)
    character = HeadCharacter(character)

    character.perfomAttack()
}



