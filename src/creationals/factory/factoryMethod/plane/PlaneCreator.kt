package creationals.factory.factoryMethod.plane

import creationals.factory.factoryMethod.VehicleCreator

class PlaneCreator: VehicleCreator() {
    override fun createVehicle(): Plane {
        return Plane()
    }
}