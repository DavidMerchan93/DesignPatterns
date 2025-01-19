package creationals.singleton

class SingletonApi {

    private var instance: SingletonApi? = null

    var url: String = ""
        private set
    var endpoint: String = ""
        private set

    private fun setValues(url: String, endpoint: String): SingletonApi {
        this.url = url
        this.endpoint = endpoint
        return this
    }

    fun getPath() = "$url/$endpoint"

    companion object {
        fun newInstance(url: String, endpoint: String): SingletonApi {
            return SingletonApi().setValues(url, endpoint)
        }
    }
}

fun main() {
    val api1 = SingletonApi.newInstance("https://api.example.com", "users")
    val api2 = SingletonApi.newInstance("https://api.examples.com", "posts")

    println(api1.getPath()) // Output: https://api.example.com/users
    println(api2.getPath()) // Output: https://api.example.com/users
}
