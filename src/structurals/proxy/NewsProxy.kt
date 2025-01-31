package structurals.proxy

import java.util.Date
import kotlin.random.Random

interface NewsService {
    fun getNews(): List<String>
}

class NewsServiceReal : NewsService {
    override fun getNews(): List<String> {
        val news = mutableListOf<String>()
        repeat(10) {
            news.add("Titular noticia ${Random.nextInt(100, 500)}")
        }
        return news
    }
}

class NewsServiceProxy(val userId: Int): NewsService {

    private val newsServiceReal = NewsServiceReal()
    private val newsCache = mutableListOf<String>()
    private var canFetchData = true

    override fun getNews(): List<String> {
        if (newsCache.isEmpty() || canFetchData) {
            println("Cargado los datos, User ID: $userId, Hora: ${Date().time}")
            newsCache.clear()
            newsCache.addAll(newsServiceReal.getNews())
            canFetchData = false
            startTimer()
        }
        return newsCache
    }

    private fun startTimer() {
        Thread {
            for (i in 10 downTo 1) {
                Thread.sleep(1000)
            }
            canFetchData = true
        }.start()
    }
}

fun main() {
    val proxy = NewsServiceProxy(123456)

    // Primera carga
    println("------------------ Primera carga --------------------")
    println(proxy.getNews().joinToString(",\n"))

    // Segunda carga antes de los 10 segundos
    // Debe mostrar el mismo listado
    println("------------------ Segunda carga --------------------")
    println(proxy.getNews().joinToString(",\n"))

    Thread.sleep(11000)

    // Tercera carga despues de los 10 segundos
    // Debe un listado mas grande
    println("------------------ Tercera carga --------------------")
    println(proxy.getNews().joinToString(",\n"))
}
