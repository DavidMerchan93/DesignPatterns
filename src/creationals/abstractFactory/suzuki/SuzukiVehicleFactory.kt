package creationals.abstractFactory.suzuki

import creationals.abstractFactory.Vehicle
import creationals.abstractFactory.VehicleFactory

class SuzukiVehicleFactory: VehicleFactory() {
    override fun getCar(): Vehicle {
        return SzCar()
    }

    override fun getMotorbike(): Vehicle {
        return SzMotorbike()
    }

    override fun getPlane(): Vehicle {
        return SzPlane()
    }
}