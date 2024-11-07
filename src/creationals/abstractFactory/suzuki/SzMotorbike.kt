package creationals.abstractFactory.suzuki

import creationals.abstractFactory.Vehicle

class SzMotorbike: Vehicle {
    override fun getBuilder(): String {
        return "Suzuki Motorbike"
    }
}