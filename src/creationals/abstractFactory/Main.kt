package creationals.abstractFactory

import creationals.abstractFactory.suzuki.SuzukiVehicleFactory
import creationals.abstractFactory.toyota.ToyotaVehicleFactory

fun main() {
    val factory: VehicleFactory = SuzukiVehicleFactory()

    val car: Vehicle = factory.getCar()
    val plane: Vehicle = factory.getPlane()
    val motorbike: Vehicle = factory.getMotorbike()

    println(car.getBuilder())
    println(plane.getBuilder())
    println(motorbike.getBuilder())
}