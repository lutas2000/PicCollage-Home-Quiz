package lutas.piccollage.quiz.q2

class RotatableRect() {

    class Point(var x: Double = 0.0, var y: Double = 0.0) {
        fun dot(point: Point): Double {
            return x * point.x + y * point.y
        }

        fun normalize(): Point {
            y *= -1
            var d = Math.sqrt(x*x + y*y)
            if (d == 0.0) {
                d = 1.0
            }
            return Point(x / d, y / d)
        }
    }

    private val points = mutableListOf<Point>()

    constructor(left: Double, top: Double, right: Double, bottom: Double, rotate: Double = 0.0): this() {
        points.add(Point(left, top))
        points.add(Point(right, top))
        points.add(Point(right, bottom))
        points.add(Point(left, bottom))
        if (rotate != 0.0) {
            val center = Point((left + right) / 2, (top + bottom) / 2)
            points.forEach { rotatePoint(it, center, rotate) }
        }
    }

    private fun rotatePoint(point: Point, originPoint: Point, rotate: Double) {
        val angle = Math.toRadians(rotate)
        val tempX = point.x - originPoint.x
        val tempY = point.y - originPoint.y
        point.x = (Math.cos(angle) * tempX - Math.sin(angle) * tempY + originPoint.x)
        point.y = (Math.sin(angle) * tempX + Math.cos(angle) * tempY + originPoint.y)
    }

    fun getX(index: Int): Float {
        return points[index].x.toFloat()
    }

    fun getY(index: Int): Float {
        return points[index].y.toFloat()
    }

    fun getAxes(): List<Point> {
        val axes = mutableListOf<Point>()
        for(i in 0 until 4) {
            val position = if(i+1==4) 0 else i+1
            val x = points[i].x - points[position].x
            val y = points[i].y - points[position].y
            val vector = Point(x, y)
            axes.add(vector.normalize())
        }
        return axes
    }

    fun getPoints(): List<Point> {
        return points
    }
}