package creationals.abstractFactory

import creationals.abstractFactory.toyota.ToyotaVehicleFactory

fun main() {
    val car: Vehicle = ToyotaVehicleFactory().getCar()
    println(car.getBuilder())
}