package lutas.piccollage.quiz.q3

class Cell(var x: Int, var y: Int, var alive: Boolean = false) {
    fun die() {
        alive = false
    }

    fun born() {
        alive = true
    }
}