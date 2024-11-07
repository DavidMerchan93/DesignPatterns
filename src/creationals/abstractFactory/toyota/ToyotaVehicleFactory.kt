package creationals.abstractFactory.toyota

import creationals.abstractFactory.Vehicle
import creationals.abstractFactory.VehicleFactory

class ToyotaVehicleFactory: VehicleFactory() {
    override fun getCar(): Vehicle {
        return TyCar()
    }

    override fun getMotorbike(): Vehicle {
        return TyMotorbike()
    }

    override fun getPlane(): Vehicle {
        return TyPlane()
    }
}