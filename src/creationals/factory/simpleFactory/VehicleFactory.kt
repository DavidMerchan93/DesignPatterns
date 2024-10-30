package creationals.factory.simpleFactory

class VehicleFactory {
    fun getVehicle(byType: VehicleType): Vehicle {
        return when(byType) {
            VehicleType.CAR -> Car()
            VehicleType.TRUCK -> Truck()
            VehicleType.PLANE -> Plane()
        }
    }
}

enum class VehicleType {
    CAR,
    TRUCK,
    PLANE
}