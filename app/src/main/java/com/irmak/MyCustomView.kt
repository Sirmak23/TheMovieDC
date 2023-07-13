package com.irmak
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import com.irmak.themoviedc.R

var text: String  = ""
var imageDrawable:Drawable? = null

class CustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val imageRect = Rect()
    private val textPaint = Paint()

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomView)
        imageDrawable = attributes.getDrawable(R.styleable.CustomView_custom_view_image)
        text = attributes.getString(R.styleable.CustomView_custom_view_text) ?: ""

        attributes.recycle()

        textPaint.textSize = resources.getDimension(R.dimen.custom_view_text_size)
        textPaint.color = resources.getColor(R.color.custom_view_text_color)
        textPaint.isAntiAlias = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val desiredWidth = 600
        val desiredHeight = 900
        val width = resolveSize(desiredWidth, widthMeasureSpec)
        val height = resolveSize(desiredHeight, heightMeasureSpec)

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        imageDrawable?.bounds = imageRect
        imageDrawable?.draw(canvas)

        val textX = (width - textPaint.measureText(text)) / 2
        val textY = 1f+70
        canvas.drawText(text, textX, textY, textPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        imageRect.set(0, 0, w, h)
    }
}
