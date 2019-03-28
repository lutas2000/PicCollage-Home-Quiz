package lutas.piccollage.quiz.q1

import org.junit.Test
import org.junit.Assert.*

class Containing7Test {

    @Test
    fun is_computer_correct() {
        val computer = Containing7()
        assertEquals(2, computer.compute(17))
        assertEquals(242, computer.compute(871))
        assertEquals(3439, computer.compute(10000))
    }
}