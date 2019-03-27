package lutas.piccollage.quiz.q1

import org.junit.Test
import org.junit.Assert.*

class Containing7Test {

    @Test
    fun is_computer_correct() {
        val computer = Containing7()
        assertEquals(0, computer.compute(5))
        assertEquals(1, computer.compute(7))
        assertEquals(19, computer.compute(100))
    }
}