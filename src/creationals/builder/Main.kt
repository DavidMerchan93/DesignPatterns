package creationals.builder

fun main() {
    val warrior = Character.Builder()
        .setName("David")
        .setType(CharacterType.WARRIOR)
        .setStrong(10)
        .setDefence(8)
        .build()

    val wizard = Character.Builder()
        .setName("Tatiana")
        .setType(CharacterType.WIZARD)
        .setMana(10)
        .setDefence(5)
        .setStrong(4)
        .build()

    val stoll = Character.Builder()
        .setName("Briam")
        .setType(CharacterType.STOLEN)
        .setVelocity(12)
        .setStrong(6)
        .setDefence(7)
        .build()

    warrior.printCharacter()
    wizard.printCharacter()
    stoll.printCharacter()
}