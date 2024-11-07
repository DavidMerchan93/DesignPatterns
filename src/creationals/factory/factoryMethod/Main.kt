package creationals.factory.factoryMethod

import creationals.factory.factoryMethod.car.Car
import creationals.factory.factoryMethod.car.CarCreator
import creationals.factory.factoryMethod.plane.Plane
import creationals.factory.factoryMethod.plane.PlaneCreator
import creationals.factory.factoryMethod.truck.Truck
import creationals.factory.factoryMethod.truck.TruckCreator

/**
 * Respeta el principio de Open-Close y el principio de Single responsability
 * Si quiero agregar un nuevo vehiculo solo debo agregar el creador y el vehiculo, no modifico el codigo
 * del creador o agregar un nuevo enum
 * Contra: Se generan mas clases, posible quebrantamiento del principio de KISS
 */
fun main() {

    val car: Vehicle = CarCreator().createVehicle()
    car.printVehicle()

    // these lines are equals

    CarCreator().doSomething()

//    val truck: Vehicle = TruckCreator().createVehicle()
//    val plane: Vehicle = PlaneCreator().createVehicle()
//
//    car.printVehicle()
//    truck.printVehicle()
//    plane.printVehicle()



}