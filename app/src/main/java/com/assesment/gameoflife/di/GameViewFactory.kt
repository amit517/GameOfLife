package com.assesment.gameoflife.di

import com.assesment.gameoflife.gamehelper.GameOfLife
import com.assesment.gameoflife.gamehelper.GameView
import dagger.assisted.AssistedFactory

@AssistedFactory
interface GameViewFactory {
    fun createGameView(game: GameOfLife) : GameView
}
