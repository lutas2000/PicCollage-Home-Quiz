package lutas.piccollage.quiz.q2

class OverlappingRect {

    fun checkOverlapping(rectA: RotatableRect, rectB: RotatableRect): Boolean {
        val axes = rectA.getAxes() + rectB.getAxes()

        for (axis in axes) {
            if (!overlap(rectA, rectB, axis)) {
                return false
            }
        }
        return true
    }

    private fun overlap(rectA: RotatableRect, rectB: RotatableRect, axis: RotatableRect.Point): Boolean {
        val projectionA = rectA.getPoints().map { axis.dot(it) }.sorted()
        val minA = projectionA[0]
        val maxA = projectionA[3]
        val projectionB = rectB.getPoints().map { axis.dot(it) }.sorted()
        val minB = projectionB[0]
        val maxB = projectionB[3]

        return !(maxB < minA || maxA < minB)
    }
}