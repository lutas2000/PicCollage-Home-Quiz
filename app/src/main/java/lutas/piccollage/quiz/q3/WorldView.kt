package lutas.piccollage.quiz.q3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class WorldView: View {

    private val paint = Paint()
    private var columns = 0
    private var rows = 0
    // TODO make fieldSize attribute
    private var fieldSize = 40f
    var world: World? = null
    var onDownListener: ((Int, Int) -> Unit)? = null
    var onLayoutListener: ((Int, Int) -> Unit)? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        columns = (width / fieldSize).toInt()
        rows = (height / fieldSize).toInt()
        onLayoutListener?.invoke(columns, rows)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBackground(canvas)
        drawLife(canvas)
        invalidate()
    }

    private fun drawBackground(canvas: Canvas?) {
        paint.color = Color.BLACK
        canvas?.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
    }

    private fun drawLife(canvas: Canvas?) {
        paint.color = Color.WHITE
        world?.getLiveCells()?.forEach {
            drawField(canvas, it.x, it.y)
        }
    }

    private fun drawField(canvas: Canvas?, column: Int, row: Int) {
        val x0 = column * fieldSize + left
        val x1 = x0 + fieldSize
        val y0 = row * fieldSize + top
        val y1 = y0 + fieldSize
        canvas?.drawRect(x0, y0, x1, y1, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                val column = ((event.x - left) / fieldSize).toInt()
                val row = ((event.y - top) / fieldSize).toInt()
                onDownListener?.invoke(column, row)
            }
        }
        return super.onTouchEvent(event)
    }
}