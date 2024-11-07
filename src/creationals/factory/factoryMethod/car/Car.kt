package creationals.factory.factoryMethod.car

import creationals.factory.factoryMethod.Vehicle

// this is my concret product
class Car: Vehicle {
    override fun printVehicle() {
        println("I'm a Car")
    }
}