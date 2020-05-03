package com.clean.cut.quiztactoe.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clean.cut.quiztactoe.R
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_about)

    // set up toolbar
    setSupportActionBar(toolbar)
    toolbar.setNavigationOnClickListener { finish() }
  }
}