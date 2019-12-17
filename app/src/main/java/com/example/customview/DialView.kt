package com.example.customview

import android.content.Context
import android.graphics.*
import android.graphics.Paint.Align
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat


/**
 * Created by Divya Yadav on 14-Dec-19.
 * Android Developer
 * divyayadav2511995@gmail.com
 */

class DialView @JvmOverloads constructor( context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    private var outerCircleRadius = 0.0f
    private var innerCircleRadius = 0.0f

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
        typeface = Typeface.create( "", Typeface.BOLD)
    }

    init {
        isClickable = true
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true
        invalidate()
        return true
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        outerCircleRadius = 70f
        innerCircleRadius = 60f
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //draws outer circle with outerCircleRadius
        paint.color = ContextCompat.getColor(context, R.color.colorPrimary)
        canvas!!.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), outerCircleRadius, paint)

        //draws inner circle with innerCircleRadius
        paint.color = Color.BLACK
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), innerCircleRadius, paint)

        //sets properties of paint to draw text
        paint.color = Color.WHITE
        paint.textAlign = Align.CENTER
        paint.textSize = 28.0f

        val textHeight = paint.descent() - paint.ascent()
        val textOffset = textHeight / 2 - paint.descent()
        val bounds = RectF(0f, 0f, width.toFloat(), height.toFloat())
        //sets position of text in the center of circle
        canvas.drawText("BOOK", bounds.centerX(), bounds.centerY() + textOffset, paint)

    }
}
