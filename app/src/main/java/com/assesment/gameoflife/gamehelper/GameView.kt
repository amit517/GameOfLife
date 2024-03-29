package com.assesment.gameoflife.gamehelper

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.qualifiers.ActivityContext
import java.util.*
import kotlin.math.max
import kotlin.math.min

@SuppressLint("ViewConstructor")
class GameView @AssistedInject constructor(
    @ActivityContext context: Context,
    @Assisted game: GameOfLife,
) : View(
    context), ScaleGestureDetector.OnScaleGestureListener {

    private var gameWorld = game.world

    private val worldWidth = 40F
    private val worldHeight = 60F
    private var columns = worldWidth
    private var rows = worldHeight
    private var cellWidth = width / columns
    private var cellHeight = height / rows

    private val scaleDetector = ScaleGestureDetector(context, this)
    var scaleFactor = 1.0f
    private val gridPaint = Paint()
    private val cellPaint = Paint()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        calculateDimensions()
        invalidate()
    }

    private fun calculateDimensions() {
        if (columns < 1 || rows < 1) return
        columns = worldWidth / scaleFactor
        rows = worldHeight / scaleFactor

        cellWidth = width / columns
        cellHeight = height / rows
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        scale(canvas)

        val backgroundPaint = Paint()
        backgroundPaint.color = Color.WHITE
        canvas.drawPaint(backgroundPaint)
        canvas.restore()

        if (columns == 0F || rows == 0F) return

        drawCells(canvas)
        drawGrid(canvas)
    }

    private fun scale(canvas: Canvas) {
        canvas.save()
        canvas.scale(scaleFactor, scaleFactor)
    }

    private fun drawCells(canvas: Canvas) {
        for (i in 0..(columns - 1).toInt()) {
            for (j in 0..(rows - 1).toInt()) {
                if (gameWorld.isAlive(Cell(i, j))) {
                    val cellRect = Rect((i * cellWidth).toInt(), (j * cellHeight).toInt(),
                        ((i + 1) * cellWidth).toInt(), ((j + 1) * cellHeight).toInt())
                    canvas.drawRect(cellRect, cellPaint)
                }
            }
        }
    }

    private fun drawGrid(canvas: Canvas) {
        gridPaint.style = Paint.Style.FILL_AND_STROKE
        for (i in 1..(columns - 1).toInt()) {
            canvas.drawLine(i * cellWidth, 0F, i * cellWidth, height.toFloat(), gridPaint)
        }
        for (i in 1..(rows - 1).toInt()) {
            canvas.drawLine(0F, i * cellHeight, width.toFloat(), i * cellHeight, gridPaint)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) return false

        if (event.action == MotionEvent.ACTION_MOVE) {
            val column = event.x / cellWidth
            val row = event.y / cellHeight

            gameWorld.addCell(Cell(column.toInt(), row.toInt()))
            invalidate()
        }

        if (event.action == MotionEvent.ACTION_DOWN) {
            cellPaint.color = getRandomColor()
        }

        scaleDetector.onTouchEvent(event)

        return true
    }

    fun nextGeneration(world: GameWorld) {
        gameWorld = world
        invalidate()
    }

    private fun getRandomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    override fun onScale(detector: ScaleGestureDetector?): Boolean {
        if (detector == null) return false

        scaleFactor *= detector.scaleFactor
        scaleFactor = max(0.4f, min(scaleFactor, 3.0f))

        calculateDimensions()
        invalidate()
        return true
    }

    override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
        return true
    }

    override fun onScaleEnd(detector: ScaleGestureDetector?) {}
}
