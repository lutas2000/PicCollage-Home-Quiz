package lutas.piccollage.quiz.q3

class World(var width: Int, var height: Int) {

    private val board = Array(width) { Array(height) { Cell(0, 0) } }

    init {
        for (x in 0 until width) {
            for (y in 0 until height) {
                board[x][y] = Cell(x, y)
            }
        }
    }

    operator fun get(i: Int, j: Int): Cell? {
        return board[i][j]
    }

    fun nextGeneration() {
        val liveCells = mutableListOf<Cell>()
        val deadCells = mutableListOf<Cell>()

        for (i in 0 until width) {
            for (j in 0 until height) {
                val cell = board[i][j]
                val aliveNeighbours = findAliveNeighbours(i, j)

                when {
                    cell.alive && aliveNeighbours < 2 -> deadCells.add(cell)
                    cell.alive && aliveNeighbours > 3 -> deadCells.add(cell)
                    cell.alive || !cell.alive && aliveNeighbours == 3 -> liveCells.add(cell)
                }
            }
        }

        liveCells.forEach { it.born() }
        deadCells.forEach { it.die() }
    }

    private fun findAliveNeighbours(x: Int, y: Int): Int {
        var neighbours = 0

        for (i in x-1..x+1) {
            for (j in y-1..y+1) {
                if ((i != x || j != y) && i >= 0
                    && i < width && j >= 0 && j < height
                ) {
                    val cell = board[i][j]
                    if (cell.alive) {
                        neighbours++
                    }
                }
            }
        }
        return neighbours
    }

    fun getLiveCells(): List<Cell> {
        return board.flatMap { array -> array.filter { it.alive } }
    }
}
