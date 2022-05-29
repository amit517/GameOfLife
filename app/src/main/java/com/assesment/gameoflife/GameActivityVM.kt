package com.assesment.gameoflife

import androidx.lifecycle.viewModelScope
import com.assesment.base.viewmodel.BaseViewModel
import com.assesment.gameoflife.gamehelper.GameOfLife
import com.assesment.gameoflife.gamehelper.GameView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameActivityVM @Inject constructor(val game: GameOfLife) : BaseViewModel() {

    fun runGame(gameView: GameView) {
        viewModelScope.launch(Dispatchers.Main) {
            game.run(gameView)
        }
    }

    fun pause() {
        game.pause()
    }
}
