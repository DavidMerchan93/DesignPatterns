package creationals.factory.factoryMethod

abstract class VehicleCreator {
    abstract fun createVehicle(): Vehicle

    fun doSomething() {
        val creator = this.createVehicle()
        creator.printVehicle()
    }
}