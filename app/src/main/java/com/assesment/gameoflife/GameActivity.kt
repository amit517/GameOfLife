package com.assesment.gameoflife

import androidx.core.content.ContextCompat
import com.assesment.base.view.BaseActivity
import com.assesment.gameoflife.databinding.ActivityGameBinding

class GameActivity : BaseActivity<ActivityGameBinding>() {

    private val gameWorld = GameWorld()
    private val game = GameOfLife(gameWorld)

    override fun getLayout(): Int = R.layout.activity_game

    override fun initOnCreateView() {
        val gameView = GameViewKotlin(this, 40F, 60F, game)
        bindingView.contentFrame.addView(gameView)
        bindingView.btnPlay.setOnClickListener { startOrPauseGame(gameView) }
    }

    private fun startOrPauseGame(gameView: GameViewKotlin) {
        if (game.isRunning) pauseGame() else startGame(gameView)
    }

    private fun startGame(gameView: GameViewKotlin) {
        game.run(gameView)
        bindingView.btnPlay.setImageDrawable(ContextCompat.getDrawable(this,
            R.drawable.ic_action_pause))
    }

    private fun pauseGame() {
        game.pause()
        bindingView.btnPlay.setImageDrawable(ContextCompat.getDrawable(this,
            R.drawable.ic_action_play))
    }
}
