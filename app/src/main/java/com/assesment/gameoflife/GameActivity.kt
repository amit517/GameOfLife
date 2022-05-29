package com.assesment.gameoflife

import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.assesment.base.view.BaseActivity
import com.assesment.gameoflife.databinding.ActivityGameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameActivity : BaseActivity<ActivityGameBinding>() {
    private val viewModel: GameActivityVM by viewModels() {
        GameActivityVMF(GameWorld())
    }

    override fun getLayout(): Int = R.layout.activity_game

    override fun initOnCreateView() {
        val gameView = GameViewKotlin(this, 40F, 60F, viewModel.game)
        bindingView.contentFrame.addView(gameView)
        bindingView.btnPlay.setOnClickListener { startOrPauseGame(gameView) }
    }

    private fun startOrPauseGame(gameView: GameViewKotlin) {
        if (viewModel.game.isRunning) pauseGame() else startGame(gameView)
    }

    private fun startGame(gameView: GameViewKotlin) {
        viewModel.game.run(gameView)
        bindingView.btnPlay.setImageDrawable(ContextCompat.getDrawable(this,
            R.drawable.ic_action_pause))
    }

    private fun pauseGame() {
        viewModel.game.pause()
        bindingView.btnPlay.setImageDrawable(ContextCompat.getDrawable(this,
            R.drawable.ic_action_play))
    }
}
