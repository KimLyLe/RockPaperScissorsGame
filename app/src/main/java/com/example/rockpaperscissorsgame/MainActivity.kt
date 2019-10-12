package com.example.rockpaperscissorsgame

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissorsgame.DB.HistoryRepository
import com.example.rockpaperscissorsgame.Model.History
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_show_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val SHOW_HISTORY_REQUEST_CODE = 100
private lateinit var historyRepository: HistoryRepository
var pcWins: Boolean = false
var userWins: Boolean = false

class MainActivity : AppCompatActivity() {

    private var pcChoice: Int = 1
    private var myChoice: Int = 1
    private var draw: Int = 0
    private var lose: Int = 0
    private var win: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        action_show_history.setOnClickListener{ startShowHistory() }
        historyRepository = HistoryRepository(this)
        initViews()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                SHOW_HISTORY_REQUEST_CODE -> {
                    val history = data!!.getParcelableExtra<History>(EXTRA_REMINDER)
                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) {
                            historyRepository.insertHistory(history)
                        }
                    }
                }
            }
            }
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
        var result: String = "Draw!"
        var history = History(result)
        insertGameIntoDatabase(history)
    }

    private fun onResultYouLose() {
        pcWins = true
        lose++
        tvLose.text = getString(R.string.tvLose, lose)
        tvAnnouncement.text = "You Lose!"
        var result: String = "You Lose!"
        var history = History(result)
        insertGameIntoDatabase(history)
    }

    private fun onResultYouWin() {
        userWins = true
        win++
        tvWin.text = getString(R.string.tvWin, win)
        tvAnnouncement.text = "You Win!"
        var result: String = "You Win!"
        var history = History(result)
        insertGameIntoDatabase(history)
    }

    private fun insertGameIntoDatabase(history: History) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                historyRepository.insertHistory(history)
            }
        }
    }

    private fun startShowHistory() {
        val intent = Intent(this, ShowHistory::class.java)
        startActivity(intent)
        startActivityForResult(intent, SHOW_HISTORY_REQUEST_CODE)
    }
}

