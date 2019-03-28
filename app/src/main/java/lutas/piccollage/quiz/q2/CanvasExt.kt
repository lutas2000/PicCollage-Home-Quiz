package lutas.piccollage.quiz.q2

import android.graphics.Canvas
import android.graphics.Paint

fun Canvas.drawRotatableRect(rect: RotatableRect, paint: Paint) {
    drawLine(rect.getX(0), rect.getY(0), rect.getX(1), rect.getY(1), paint)
    drawLine(rect.getX(1), rect.getY(1), rect.getX(2), rect.getY(2), paint)
    drawLine(rect.getX(2), rect.getY(2), rect.getX(3), rect.getY(3), paint)
    drawLine(rect.getX(3), rect.getY(3), rect.getX(0), rect.getY(0), paint)
}