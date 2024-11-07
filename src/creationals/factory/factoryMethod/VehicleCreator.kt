package creationals.factory.factoryMethod

// this is my creator
abstract class VehicleCreator {
    abstract fun createVehicle(): Vehicle

    fun doSomething() {
        val creator = this.createVehicle()
        creator.printVehicle()
    }
}