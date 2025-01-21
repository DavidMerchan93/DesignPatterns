package creationals.singleton

class SingletonApi {

    var url: String = ""
        private set
    var endpoint: String = ""
        private set

    fun setValues(url: String, endpoint: String): SingletonApi {
        if (this.url.isEmpty()) {
            this.url = url
        }
        this.endpoint = endpoint
        return this
    }

    fun getPath() = "$url/$endpoint"

    companion object {
        private var instance: SingletonApi? = null

        fun getInstance(): SingletonApi {
            if (instance == null) {
                instance = SingletonApi()
            }
            return instance!!
        }
    }
}

fun main() {

    val mapa: Map<String, Int> = mapOf()
    val hashMapa: HashMap<String, Int> = hashMapOf()


    val api1 = SingletonApi.getInstance()
    val api2 = SingletonApi.getInstance()

    api1.setValues("https://api.examples.com", "get")
    api2.setValues("https://api.examplesss.com", "posts")

    println(api1.getPath()) // Output: https://api.example.com/users
    println(api2.getPath()) // Output: https://api.example.com/users
}
