package com.assesment.gameoflife.gamehelper

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameOfLife @Inject constructor(var world: GameWorld) {
    var isRunning = false

    private fun nextGeneration(): GameWorld {
        val nextWorld = world.nextGeneration()
        world = nextWorld
        return world
    }

    suspend fun run(gameView: GameView) {
        isRunning = true
        runOffMainThead(gameView)
    }

    private suspend fun runOffMainThead(gameView: GameView) = withContext(Dispatchers.Main) {
        while (isRunning) {
            generationLifeSpan()
            world = nextGeneration()
            gameView.nextGeneration(world)
        }
    }

    fun pause() {
        isRunning = false
    }

    private suspend fun generationLifeSpan() = withContext(Dispatchers.Main) {
        delay(100)
    }
}
