package creationals.abstractFactory.toyota

import creationals.abstractFactory.Vehicle

class TyCar: Vehicle {
    override fun getBuilder(): String {
        return "Car Toyota"
    }
}