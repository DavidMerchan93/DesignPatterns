package creationals.abstractFactory.suzuki

import creationals.abstractFactory.Vehicle

class SzPlane: Vehicle {
    override fun getBuilder(): String {
        return "Suzuki Plane"
    }
}