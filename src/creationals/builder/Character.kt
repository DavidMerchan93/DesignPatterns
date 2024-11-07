package creationals.builder

enum class CharacterType(val data: String) {
    WARRIOR("Guerrero"),
    WIZARD("Mago"),
    STOLEN("Ladron"),
    ORC("Orco"),
    DEFAULT("other")
}

class Character private constructor(
    private val name: String,
    private val type: CharacterType,
    private val strong: Int,
    private val velocity: Int,
    private val defence: Int,
    private val mana: Int,
    private val smell: Int
) {
    fun printCharacter() {
        val data = """
            name: $name,
            type: ${type.data},
            strong: $strong,
            velocity: $velocity,
            defence: $defence,
            mana: $mana,
            smell: $smell
        """.trimIndent()
        println(data)
    }

    class Builder {
        private var name = ""
        private var type = CharacterType.DEFAULT
        private var strong = 0
        private var velocity = 0
        private var defence = 0
        private var mana = 0
        private var smell = 0

        fun setName(name: String) = apply { this.name = name }
        fun setType(type: CharacterType) = apply { this.type = type }
        fun setStrong(strong: Int) = apply { this.strong = strong }
        fun setVelocity(velocity: Int) = apply { this.velocity = velocity }
        fun setDefence(defence: Int) = apply { this.defence = defence }
        fun setMana(mana: Int) = apply { this.mana = mana }
        fun setSmell(smell: Int) = apply { this.smell = smell }

        fun build() = Character(name, type, strong, velocity, defence, mana, smell)

    }
}