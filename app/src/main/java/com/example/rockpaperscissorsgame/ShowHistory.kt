package com.example.rockpaperscissorsgame

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_show_history.*
import kotlinx.android.synthetic.main.history_item.*

const val EXTRA_REMINDER = "EXTRA_REMINDER"

class ShowHistory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_history)
        initViews()
    }
    private fun initViews() {
        // onClickListener for delete button
        // onClickListener to go back to home screen
    }
}
