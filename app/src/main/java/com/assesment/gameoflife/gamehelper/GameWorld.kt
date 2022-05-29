package com.assesment.gameoflife.gamehelper

import java.util.*


class GameWorld(private val aliveCells: HashSet<Cell> = hashSetOf()) {

    fun addCell(cell: Cell) = aliveCells.add(cell)

    private fun getNeighbours(cell: Cell): Set<Cell> {
        val neighbours: HashSet<Cell> = hashSetOf()

        getNeighbourhood(cell).filter { isAlive(it) }.forEach { neighbours.add(it) }
        neighbours.remove(cell)
        return neighbours
    }

    private fun getNeighbourhood(cell: Cell): Set<Cell> {
        val neighbourhood: HashSet<Cell> = hashSetOf()

        for (dx in -1..1) {
            for (dy in -1..1) {
                neighbourhood.add(Cell(cell.xCoordinate + dx, cell.yCoordinate + dy))
            }
        }
        return neighbourhood
    }

    fun isAlive(cell: Cell): Boolean {
        var isAlive = false

        for ((xCoordinate, yCoordinate) in aliveCells) {
            if (xCoordinate == cell.xCoordinate && yCoordinate == cell.yCoordinate) {
                isAlive = true
                break
            }
        }
        return isAlive
    }

    fun nextGeneration(): GameWorld {
        val world = GameWorld()
        val potentialCellsToReborn: HashSet<Cell> = hashSetOf()

        for (cell in aliveCells) {
            if (shouldBeAliveInNextGeneration(cell)) {
                world.addCell(cell)
            }

            potentialCellsToReborn.addAll(getNeighbourhood(cell))
        }

        potentialCellsToReborn.filter { shouldBornInNextGeneration(it) }.forEach { world.addCell(it) }

        return world
    }

    private fun shouldBeAliveInNextGeneration(cell: Cell) = getNeighbours(cell).size == 2 || getNeighbours(
            cell).size == 3


    private fun shouldBornInNextGeneration(cell: Cell) = !isAlive(cell) && getNeighbours(cell).size == 3
}
