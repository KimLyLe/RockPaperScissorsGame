package com.example.rockpaperscissorsgame

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissorsgame.DB.HistoryRepository
import com.example.rockpaperscissorsgame.Model.History
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_show_history.*
import kotlinx.android.synthetic.main.history_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val EXTRA_REMINDER = "EXTRA_REMINDER"

class ShowHistory : AppCompatActivity() {

    private val histories = arrayListOf<History>()
    private val historyAdapter = HistoryAdapter(histories)
    private lateinit var historyRepository: HistoryRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_history)
        setSupportActionBar(toolbar)
        historyRepository = HistoryRepository(this)
        initViews()
    }
    private fun initViews() {
        rvHistory.layoutManager = LinearLayoutManager(this@ShowHistory, RecyclerView.VERTICAL, false)
        rvHistory.adapter = historyAdapter
        rvHistory.addItemDecoration(DividerItemDecoration(this@ShowHistory, DividerItemDecoration.VERTICAL))
        getHistoryFromDatabase()
    }

    private fun getHistoryFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val histories = withContext(Dispatchers.IO) { historyRepository.getAllHistories() }
            this@ShowHistory.histories.clear()
            this@ShowHistory.histories.addAll(histories)
            historyAdapter.notifyDataSetChanged()
        }
    }
}
