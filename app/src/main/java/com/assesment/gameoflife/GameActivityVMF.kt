package com.assesment.gameoflife

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assesment.gameoflife.gamehelper.GameWorld

class GameActivityVMF @Inject constructor(private val gameWorld: GameWorld) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameActivityVM::class.java))
            return GameActivityVM(gameWorld) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
