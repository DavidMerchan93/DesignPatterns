package structurals.flyweight

import kotlin.random.Random

interface Tile {
    fun render(x: Int, y: Int, size: Int)
}

class SpecificTile(
    private val color: String
): Tile {
    override fun render(x: Int, y: Int, size: Int) {
        println("La baldosa de color $color, esta ubicada en x:$x, y:$y, y mide $size cm.")
    }
}

class TileFactory {
    private val tiles = mutableMapOf<String, Tile>()

    fun getTile(color: String): Tile {
        return tiles.getOrPut(color) {
            println("Creando baldosa $color")
            SpecificTile(color)
        }
    }
}

fun main() {
    val tileFactory = TileFactory()

    tileFactory.getTile("Blanca").render(Random.nextInt(10,20), Random.nextInt(10,20), Random.nextInt(10,20))
    tileFactory.getTile("Blanca").render(Random.nextInt(10,20), Random.nextInt(10,20), Random.nextInt(10,20))
    tileFactory.getTile("Blanca").render(Random.nextInt(10,20), Random.nextInt(10,20), Random.nextInt(10,20))
    tileFactory.getTile("Negra").render(Random.nextInt(10,20), Random.nextInt(10,20), Random.nextInt(10,20))
    tileFactory.getTile("Negra").render(Random.nextInt(10,20), Random.nextInt(10,20), Random.nextInt(10,20))
    tileFactory.getTile("Blanca").render(Random.nextInt(10,20), Random.nextInt(10,20), Random.nextInt(10,20))
    tileFactory.getTile("Blanca").render(Random.nextInt(10,20), Random.nextInt(10,20), Random.nextInt(10,20))
    tileFactory.getTile("Blanca").render(Random.nextInt(10,20), Random.nextInt(10,20), Random.nextInt(10,20))
    tileFactory.getTile("Roja").render(Random.nextInt(10,20), Random.nextInt(10,20), Random.nextInt(10,20))
    tileFactory.getTile("Azul").render(Random.nextInt(10,20), Random.nextInt(10,20), Random.nextInt(10,20))
    tileFactory.getTile("Azul").render(Random.nextInt(10,20), Random.nextInt(10,20), Random.nextInt(10,20))
    tileFactory.getTile("Azul").render(Random.nextInt(10,20), Random.nextInt(10,20), Random.nextInt(10,20))
    tileFactory.getTile("Azul").render(Random.nextInt(10,20), Random.nextInt(10,20), Random.nextInt(10,20))
    tileFactory.getTile("Azul").render(Random.nextInt(10,20), Random.nextInt(10,20), Random.nextInt(10,20))
    tileFactory.getTile("Roja").render(Random.nextInt(10,20), Random.nextInt(10,20), Random.nextInt(10,20))

}
