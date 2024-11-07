package creationals.abstractFactory.suzuki

import creationals.abstractFactory.Vehicle

class SzCar: Vehicle {
    override fun getBuilder(): String {
        return "Suzuki Car"
    }
}