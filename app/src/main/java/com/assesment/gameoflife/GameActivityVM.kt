package com.assesment.gameoflife

import androidx.lifecycle.viewModelScope
import com.assesment.base.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameActivityVM(gameWorld: GameWorld) : BaseViewModel() {
    val game = GameOfLife(gameWorld)

    fun runGame(gameView: GameView) {
        viewModelScope.launch(Dispatchers.Main) {
            game.run(gameView)
        }
    }

    fun pause() {
        game.pause()
    }
}
