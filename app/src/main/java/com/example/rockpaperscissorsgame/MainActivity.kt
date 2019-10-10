package com.example.rockpaperscissorsgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rockpaperscissorsgame.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var pcChoice: Int = 1
    private var myChoice: Int = 1
    private var draw: Int = 0
    private var lose: Int = 0
    private var win: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        ivScissors.setOnClickListener {
            onScissorsClick()
        }
        ivRock.setOnClickListener {
            onRockClick()
        }
        ivPaper.setOnClickListener {
            onPaperClick()
        }
    }

    private fun pcChoice() {
        pcChoice = (1..3).random()
        when (pcChoice) {
            1 -> ivPC.setImageResource(R.drawable.paper)
            2 -> ivPC.setImageResource(R.drawable.rock)
            3 -> ivPC.setImageResource(R.drawable.scissors)
        }
    }

    private fun onScissorsClick() {
        ivYou.setImageResource(R.drawable.scissors)
        pcChoice()
        if ( pcChoice == 3 ) {
            onResultDraw()
        } else if ( pcChoice == 2 ) {
            onResultYouLose()
        } else {
            onResultYouWin()
        }
    }

    private fun onRockClick() {
        ivYou.setImageResource(R.drawable.rock)
        pcChoice()
        if ( pcChoice == 2 ) {
            onResultDraw()
        } else if ( pcChoice == 1 ) {
            onResultYouLose()
        } else {
            onResultYouWin()
        }
    }

    private fun onPaperClick() {
        ivYou.setImageResource(R.drawable.paper)
        pcChoice()
        if ( pcChoice == 1 ) {
            onResultDraw()
        } else if ( pcChoice == 3 ) {
            onResultYouLose()
        } else {
            onResultYouWin()
        }
    }

    private fun onResultDraw() {
        draw++
        tvDraw.text = getString(R.string.tvDraw, draw)
        tvAnnouncement.text = "Draw!"
    }

    private fun onResultYouLose() {
        lose++
        tvLose.text = getString(R.string.tvLose, lose)
        tvAnnouncement.text = "You Lose!"
    }

    private fun onResultYouWin() {
        win++
        tvWin.text = getString(R.string.tvWin, win)
        tvAnnouncement.text = "You Win!"
    }
}

