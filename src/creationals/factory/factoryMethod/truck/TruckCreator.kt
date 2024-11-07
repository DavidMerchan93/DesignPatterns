package creationals.factory.factoryMethod.truck

import creationals.factory.factoryMethod.VehicleCreator

class TruckCreator: VehicleCreator() {
    override fun createVehicle(): Truck {
        return Truck()
    }
}