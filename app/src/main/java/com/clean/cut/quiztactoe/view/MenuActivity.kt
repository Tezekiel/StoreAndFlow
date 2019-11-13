package com.clean.cut.quiztactoe.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.clean.cut.quiztactoe.R

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun startActivityLocalGameSetup(view: View) {
        startActivity(Intent(this, LocalGameSetupActivity::class.java))
    }

    fun closeApp(view: View) {
        finish()
    }


}
