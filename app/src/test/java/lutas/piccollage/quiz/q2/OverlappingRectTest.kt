package lutas.piccollage.quiz.q2

import android.graphics.Rect
import org.junit.Test
import org.junit.Assert.*

class OverlappingRectTest {

    private val checker = OverlappingRect()

    @Test
    fun test_overlapping() {
        val rectA = Rect(0, 0, 100, 100)
        val rectB = Rect(50, 0, 150, 100)
        val isOverlapping = checker.checkOverlapping(rectA, rectB)
        assertEquals(true, isOverlapping)
    }

    @Test
    fun test_intersecting() {
        val rectA = Rect(0, 0, 100, 100)
        val rectB = Rect(100, 0, 200, 100)
        val isOverlapping = checker.checkOverlapping(rectA, rectB)
        assertEquals(true, isOverlapping)
    }

    @Test
    fun test_independent() {
        val rectA = Rect(0, 0, 100, 100)
        val rectB = Rect(101, 0, 201, 100)
        val isOverlapping = checker.checkOverlapping(rectA, rectB)
        assertEquals(false, isOverlapping)
    }
}