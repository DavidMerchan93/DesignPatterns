package creationals.factory.factoryMethod

class TruckCreator: VehicleCreator() {
    override fun createVehicle(): Truck {
        return Truck()
    }
}