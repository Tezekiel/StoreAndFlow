package com.clean.cut.quiztactoe.menu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.clean.cut.quiztactoe.R
import com.clean.cut.quiztactoe.about.AboutActivity
import com.clean.cut.quiztactoe.localgame.setup.LocalGameSetupActivity
import com.clean.cut.quiztactoe.login.LoginViewModel
import com.clean.cut.quiztactoe.util.setSafeOnClickListener
import kotlinx.android.synthetic.main.activity_menu.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuActivity : AppCompatActivity() {
  private val viewModel by viewModel<LoginViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_menu)

    //listeners
    exit_btn.setOnClickListener { finish() }
    local_game_btn.setSafeOnClickListener { startActivity(Intent(this, LocalGameSetupActivity::class.java)) }
    about_btn.setSafeOnClickListener { startActivity(Intent(this, AboutActivity::class.java)) }

    viewModel.savedName.observe(this, Observer { name_tv.text = it })
  }
}
