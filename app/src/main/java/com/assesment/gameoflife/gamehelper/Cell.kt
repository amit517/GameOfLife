package com.assesment.gameoflife.gamehelper


data class Cell(val xCoordinate: Int, val yCoordinate: Int) {

    override fun equals(other: Any?): Boolean {
        val cell: Cell = other as Cell

        if (xCoordinate != cell.xCoordinate) return false
        return yCoordinate == cell.yCoordinate
    }
}
