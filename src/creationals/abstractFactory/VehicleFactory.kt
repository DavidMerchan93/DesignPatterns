package creationals.abstractFactory

abstract class VehicleFactory {
    abstract fun getCar(): Vehicle
    abstract fun getMotorbike(): Vehicle
    abstract fun getPlane(): Vehicle
}