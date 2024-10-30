package creationals.factory.simpleFactory

/**
 * No se respeta el principio de open-close
 * Cada vez que se quiera crear un nuevo vehiculo se debe modificar VehicleFactory
 * El codigo queda acoplado en la factoria
 */

fun main() {
    val vehicleFactory = VehicleFactory()

    vehicleFactory.getVehicle(VehicleType.CAR).printVehicle()
    vehicleFactory.getVehicle(VehicleType.TRUCK).printVehicle()
    vehicleFactory.getVehicle(VehicleType.PLANE).printVehicle()

}