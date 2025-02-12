package behaviour.memento

enum class Figure {
    LINE,
    RECTANGLE,
    CIRCLE,
    TRIANGLE,
    DEFAULT
}

/**
 * This class represents a canvas where figures can be drawn and undone.
 * It uses the Memento design pattern to save and restore the state of the canvas.
 *
 * @param historyManager The HistoryManager instance that manages the canvas states.
 */
class Canvas(
    private val historyManager: HistoryManager
) {

    private var currentFigure = Figure.DEFAULT

    fun draw(figure: Figure) {
        println("El usuario dibuja: $figure")
        currentFigure = figure
        historyManager.save(CanvasState(currentFigure))
    }

    fun undo() {
        historyManager.undo()?.let { state ->
            currentFigure = state.figure
            println("Dibujo restaurado: $currentFigure")
        }
    }
}

/**
 * This is the memento file that represent a state of the canvas
 */
data class CanvasState(val figure: Figure)

/**
 * Manages the history of canvas states for the Memento design pattern.
 * It allows saving and retrieving the last drawn figure, simulating an undo operation.
 */
class HistoryManager {

    private val history = mutableListOf<CanvasState>()

    fun save(canvasState: CanvasState) {
        history.add(canvasState)
        println("Estado guardado ${canvasState.figure}")
    }

    fun undo(): CanvasState? {
        if (history.isEmpty()) {
            println("No hay estados para deshacer")
            return null
        }
        history.removeLast()
        return history.lastOrNull()
    }
}

fun main() {
    val historyManager = HistoryManager()
    val canvas = Canvas(historyManager)

    canvas.draw(Figure.LINE)
    canvas.draw(Figure.RECTANGLE)
    canvas.draw(Figure.CIRCLE)
    canvas.draw(Figure.TRIANGLE)
    canvas.draw(Figure.LINE)
    canvas.undo()
    canvas.draw(Figure.CIRCLE)

    canvas.draw(Figure.RECTANGLE)

    canvas.undo()
    canvas.undo()
    canvas.undo()
    canvas.undo()
    canvas.undo()
    canvas.undo()
    canvas.undo()
    canvas.undo()
}
