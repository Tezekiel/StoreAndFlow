package com.clean.cut.quiztactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun startActivityLocalGameSetup(view: View) {
        startActivity(Intent(this, LocalGameSetupActivity::class.java))
    }
}
