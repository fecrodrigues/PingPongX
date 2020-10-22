package br.com.creativedevmind.pingpongx

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var playerOneScore = 0
    private var playerTwoScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpPlayers()
        setUpListeners()
        setUpValues(savedInstanceState)
    }

    //Antes de destruir a activity salva as variaveis
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("PLAYER_ONE_SCORE", playerOneScore)
        outState.putInt("PLAYER_TWO_SCORE", playerTwoScore)
    }

    private fun setUpValues(savedInstanceState: Bundle?) {
        if(savedInstanceState != null) {
            playerOneScore = savedInstanceState.getInt("PLAYER_ONE_SCORE")
            playerTwoScore = savedInstanceState.getInt("PLAYER_TWO_SCORE")
            setUpScorePlayerOne()
            setUpScorePlayerTwo()
        }
    }

    private fun setUpListeners() {
        btPlayerOneScore.setOnClickListener {
            playerOneScore++
            setUpScorePlayerOne()
        }

        btPlayerTwoScore.setOnClickListener {
            playerTwoScore++
            setUpScorePlayerTwo()
        }

        btFinishMatch.setOnClickListener {
            val responseIntent = Intent()
            setResult(Activity.RESULT_OK, responseIntent)
            finish()
        }

        btRevenge.setOnClickListener {
            revenge()
        }

        ivBack.setOnClickListener {
            val responseIntent = Intent()
            setResult(Activity.RESULT_CANCELED, responseIntent)
            finish()
        }
    }

    private fun setUpScorePlayerOne() {
        tvPlayerOneScore.text = playerOneScore.toString()
    }

    private fun setUpScorePlayerTwo() {
        tvPlayerTwoScore.text = playerTwoScore.toString()
    }

    private fun revenge() {
        playerOneScore = 0
        playerTwoScore = 0
        setUpScorePlayerOne()
        setUpScorePlayerTwo()
    }

    private fun setUpPlayers() {
        tvPlayerOneName.text = intent.getStringExtra("PLAYER1")
        tvPlayerTwoName.text = intent.getStringExtra("PLAYER2")
    }

}