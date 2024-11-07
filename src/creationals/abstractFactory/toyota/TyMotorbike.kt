package creationals.abstractFactory.toyota

import creationals.abstractFactory.Vehicle

class TyMotorbike: Vehicle {
    override fun getBuilder(): String {
        return "Motorbike Toyota"
    }
}