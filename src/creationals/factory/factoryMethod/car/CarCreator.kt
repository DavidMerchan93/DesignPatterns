package creationals.factory.factoryMethod.car

import creationals.factory.factoryMethod.VehicleCreator

//this is my concret creator
class CarCreator : VehicleCreator() {
    override fun createVehicle(): Car {
        return Car()
    }
}