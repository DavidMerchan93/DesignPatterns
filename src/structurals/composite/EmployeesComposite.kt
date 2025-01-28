package structurals.composite

/**
 * Permite componer objetos en estructuras de arbol y trabajar con estructuras
 * como si fueran objetos independientes
 *
 * Este patron aboga por el uso de la recursibidad entre objetos, un objeto puede agregar
 * varios objetos y esos objetos pueden ser le mismo tipo.
 */

interface Employee {
    fun getEmployeeCount(): Int
    fun getEmployeeName(): String
}

class EmployeeLeaf(val name: String) : Employee {
    override fun getEmployeeCount(): Int {
        return 1
    }

    override fun getEmployeeName(): String {
        return name
    }
}

class Manager(val name: String): Employee {

    private val employees = mutableListOf<Employee>()

    override fun getEmployeeCount(): Int {
        return employees.sumOf { it.getEmployeeCount() }
    }

    override fun getEmployeeName(): String {
        return buildString {
            append(name)
            employees.forEach {
                append("\n  \n")
                append(it.getEmployeeName().replace("\n","\n   "))
            }
        }
    }

    fun findLastLeaf(): Employee? {
        if (employees.isEmpty())
            return null

        val last = employees.last()

        if (last is Manager)
            return last.findLastLeaf()

        return last
    }

    fun addEmployee(employee: Employee) {
        employees.add(employee)
    }
}

fun main() {
    val ceo = Manager("CEO")
    val vpMarketing = Manager("VP of Marketing")
    val marketingSpecialist1 = EmployeeLeaf("Marketing Specialist 1")
    val marketingSpecialist2 = EmployeeLeaf("Marketing Specialist 2")

    val vpEngineering = Manager("VP of Engineering")
    val softwareEngineer1 = EmployeeLeaf("Software Engineer 1")
    val softwareEngineer2 = EmployeeLeaf("Software Engineer 2")

    val engineeringManager = Manager("Engineer Manager")
    val qaEngineer = EmployeeLeaf("QA Engineer")

    engineeringManager.addEmployee(qaEngineer)

    vpEngineering.addEmployee(softwareEngineer1)
    vpEngineering.addEmployee(softwareEngineer2)
    vpEngineering.addEmployee(engineeringManager)

    vpMarketing.addEmployee(marketingSpecialist1)
    vpMarketing.addEmployee(marketingSpecialist2)

    ceo.addEmployee(vpMarketing)
    ceo.addEmployee(vpEngineering)

    println("-------------- All employees ---------------")
    println(ceo.getEmployeeName())

    println("-------------- Last employee ---------------")
    println(ceo.findLastLeaf()?.getEmployeeName())
}
