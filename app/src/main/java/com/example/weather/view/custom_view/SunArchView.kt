package com.example.weather.view.custom_view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.weather.R
import java.util.*
import kotlin.math.sqrt


class SunArchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = R.attr.sunArchView,
    defStyleRes: Int = R.style.SunArchView
) : View(context, attrs, defStyleAttrs, defStyleRes) {

    private val pathArch = Path()
    private var progressSun: Float = 0f
    private var centerIconX: Float = 0f
    private var centerIconY: Float = 0f
    private val icon: Bitmap
    private val paintArch: Paint = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.style = Paint.Style.STROKE
        it.strokeWidth = 5f
        it.pathEffect = DashPathEffect(floatArrayOf(15f,20f), 0f)
    }
    private val paintBackgroundIcon = Paint(Paint.ANTI_ALIAS_FLAG).also {
        it.style = Paint.Style.FILL_AND_STROKE
    }

    fun setProgressSun(sunrise: Date, sunset: Date, currentTime: Date){
        val end = sunset.time - sunrise.time
        val current = currentTime.time - sunrise.time
        progressSun = current / end.toFloat()
        Log.e("TAG", "Progress $progressSun, $current, $end, ${this.width}")
        Log.e("TAG", "SET CENTER ICON $centerIconX - $centerIconY")
        invalidate()
    }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SunArchView, defStyleAttrs, defStyleRes)
        val iconDrawable = typedArray.getDrawable(R.styleable.SunArchView_iconSun)
            ?: resources.getDrawable(R.drawable.ic_sun, context.theme)
        val colorIcon = typedArray.getColor(R.styleable.SunArchView_colorIconSun, Color.YELLOW)
        val colorArch = typedArray.getColor(R.styleable.SunArchView_colorArch, Color.YELLOW)
        val colorBackgroundIcon = typedArray.getColor(R.styleable.SunArchView_backgroundIconSun, Color.TRANSPARENT)
        typedArray.recycle()

        iconDrawable.mutate().colorFilter = BlendModeColorFilterCompat
            .createBlendModeColorFilterCompat(colorIcon, BlendModeCompat.SRC_ATOP)
        icon = Bitmap.createScaledBitmap(
            iconDrawable.toBitmap(),
            MARGIN.toInt() * 2,
            MARGIN.toInt() * 2,
            true)

        paintBackgroundIcon.color = colorBackgroundIcon
        paintArch.color = colorArch
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        pathArch.reset()
        val rectArch = RectF().also {
            it.set(MARGIN, MARGIN, w.toFloat() - MARGIN, h.toFloat() * 2 - MARGIN)
        }
        pathArch.addArc(rectArch,-180f,180f)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.e("TAG", "CENTER ICON $centerIconX - $centerIconY")
        setCenterIcon(width, height, progressSun)
        canvas?.drawPath(pathArch, paintArch)
        canvas?.drawCircle(centerIconX, centerIconY, MARGIN, paintBackgroundIcon)
        canvas?.drawBitmap(icon, centerIconX - MARGIN, centerIconY - MARGIN, paintArch)
    }

    private fun setCenterIcon(width: Int, height: Int, progressSun: Float){
        centerIconX = width * progressSun
        centerIconY = height - getEllipseY(
            A = (width.toFloat() / 2) - MARGIN,
            B = height.toFloat() - MARGIN,
            x = if (centerIconX > width / 2) centerIconX - (width.toFloat() / 2)
            else centerIconX - (width.toFloat() / 2)
        )
    }

    private fun getEllipseY(A: Float, B: Float, x: Float) =
        sqrt(B * B * (1 - ((x * x) / (A * A))))

    companion object{
        private const val MARGIN = 30f
    }
}