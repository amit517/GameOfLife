package com.assesment.gameoflife

import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.assesment.base.view.BaseActivity
import com.assesment.gameoflife.databinding.ActivityGameBinding
import com.assesment.gameoflife.di.GameViewFactory
import com.assesment.gameoflife.gamehelper.GameView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GameActivity : BaseActivity<ActivityGameBinding>() {
    @Inject
    lateinit var gameViewFactory: GameViewFactory

    private val viewModel: GameActivityVM by viewModels()

    override fun getLayout(): Int = R.layout.activity_game

    override fun initOnCreateView() {
        val gameView = gameViewFactory.createGameView(viewModel.game)
        bindingView.contentFrame.addView(gameView)
        bindingView.btnPlay.setOnClickListener { startOrPauseGame(gameView) }
    }

    private fun startOrPauseGame(gameView: GameView) {
        if (viewModel.game.isRunning) pauseGame() else startGame(gameView)
    }

    private fun startGame(gameView: GameView) {
        viewModel.runGame(gameView)
        bindingView.btnPlay.setImageDrawable(ContextCompat.getDrawable(this,
            R.drawable.ic_action_pause))
    }

    private fun pauseGame() {
        viewModel.pause()
        bindingView.btnPlay.setImageDrawable(ContextCompat.getDrawable(this,
            R.drawable.ic_action_play))
    }
}
