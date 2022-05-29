package com.assesment.gameoflife

import com.assesment.base.viewmodel.BaseViewModel

class GameActivityVM(gameWorld: GameWorld) : BaseViewModel() {
    val game = GameOfLife(gameWorld)


}
