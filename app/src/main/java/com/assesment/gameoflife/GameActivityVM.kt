package com.assesment.gameoflife

import androidx.lifecycle.viewModelScope
import com.assesment.base.viewmodel.BaseViewModel
import com.assesment.gameoflife.gamehelper.GameOfLife
import com.assesment.gameoflife.gamehelper.GameView
import com.assesment.gameoflife.gamehelper.GameWorld
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameActivityVM @Inject constructor(gameWorld: GameWorld) : BaseViewModel() {
    val game: GameOfLife

    init {
        game = GameOfLife(gameWorld)
    }

    fun runGame(gameView: GameView) {
        viewModelScope.launch(Dispatchers.Main) {
            game.run(gameView)
        }
    }

    fun pause() {
        game.pause()
    }
}
