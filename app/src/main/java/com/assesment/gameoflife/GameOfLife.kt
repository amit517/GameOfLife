package com.assesment.gameoflife

import android.os.Handler
import javax.inject.Inject

class GameOfLife @Inject constructor(var world: GameWorld) {
    var isRunning = true
    private val myOffMainThreadHandler: Handler = Handler()

    private fun nextGeneration(): GameWorld {
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
