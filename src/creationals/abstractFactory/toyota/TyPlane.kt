package creationals.abstractFactory.toyota

import creationals.abstractFactory.Vehicle


class TyPlane: Vehicle {
    override fun getBuilder(): String {
        return "Plane Toyota"
    }

}