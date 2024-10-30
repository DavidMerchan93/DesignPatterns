package creationals.factory.factoryMethod

/**
 * Respeta el principio de Open-Close y el principio de Single responsability
 * Si quiero agregar un nuevo vehiculo solo debo agregar el creador y el vehiculo, no modifico el codigo
 * del creador o agregar un nuevo enum
 * Contra: Se generan mas clases, posible quebrantamiento del principio de KISS
 */
fun main() {
    val car: Car = CarCreator().createVehicle()
    val truck: Truck = TruckCreator().createVehicle()
    val plane: Plane = PlaneCreator().createVehicle()

    car.printVehicle()
    truck.printVehicle()
    plane.printVehicle()
}