package lutas.piccollage.quiz.q2

import org.junit.Test
import org.junit.Assert.*

class OverlappingRectTest {

    private val checker = OverlappingRect()

    @Test
    fun test_overlapping() {
        val rectA = RotatableRect(0.0, 0.0, 100.0, 100.0)
        val rectB = RotatableRect(50.0, 0.0, 150.0, 100.0)
        val isOverlapping = checker.checkOverlapping(rectA, rectB)
        assertEquals(true, isOverlapping)
    }

    @Test
    fun test_intersecting() {
        val rectA = RotatableRect(0.0, 0.0, 100.0, 100.0)
        val rectB = RotatableRect(100.0, 0.0, 200.0, 100.0)
        val isOverlapping = checker.checkOverlapping(rectA, rectB)
        assertEquals(true, isOverlapping)
    }

    @Test
    fun test_independent() {
        val rectA = RotatableRect(0.0, 0.0, 100.0, 100.0)
        val rectB = RotatableRect(101.0, 0.0, 201.0, 100.0)
        val isOverlapping = checker.checkOverlapping(rectA, rectB)
        assertEquals(false, isOverlapping)
    }

    @Test
    fun test_rotate_overlapping() {
        val rectA = RotatableRect(0.0, 0.0, 100.0, 500.0, 45.0)
        val rectB = RotatableRect(110.0, 0.0, 210.0, 500.0, -45.0)
        val isOverlapping = checker.checkOverlapping(rectA, rectB)
        assertEquals(true, isOverlapping)
    }

    @Test
    fun test_rotate_independent() {
        val rectA = RotatableRect(0.0, 0.0, 100.0, 500.0, -10.0)
        val rectB = RotatableRect(190.0, 0.0, 290.0, 500.0, 10.0)
        val isOverlapping = checker.checkOverlapping(rectA, rectB)
        assertEquals(false, isOverlapping)
    }
}