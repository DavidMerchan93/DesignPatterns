package creationals.factory.factoryMethod

class CarCreator : VehicleCreator() {
    override fun createVehicle(): Car {
        return Car()
    }
}