package creationals.factory.factoryMethod

class PlaneCreator: VehicleCreator() {
    override fun createVehicle(): Plane {
        return Plane()
    }
}