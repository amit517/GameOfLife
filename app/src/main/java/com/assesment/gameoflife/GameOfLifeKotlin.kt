package com.assesment.gameoflife

import android.os.Handler

class GameOfLifeKotlin(var world: GameWorldKotlin) {
    var isRunning = true
    private val myOffMainThreadHandler: Handler = Handler()

    private fun nextGeneration(): GameWorldKotlin {
        val nextWorld = world.nextGeneration()
        world = nextWorld

        return world
    }

    fun run(gameView: GameViewKotlin) {
        isRunning = true
        runOffMainThead(gameView)
    }

    private fun runOffMainThead(gameView: GameViewKotlin) = Thread(Runnable {
        while (isRunning) {
            generationLifeSpan()
            myOffMainThreadHandler.post {
                world = nextGeneration()
                gameView.nextGeneration(world)
            }
        }
    }).start()

    fun pause() {
        isRunning = false
    }

    private fun generationLifeSpan() = try {
        Thread.sleep(100)
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }
}
