package com.clean.cut.quiztactoe.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.clean.cut.quiztactoe.R
import com.clean.cut.quiztactoe.menu.MenuActivity
import com.clean.cut.quiztactoe.util.toText
import kotlinx.android.synthetic.main.activity_login_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
  private val viewModel by viewModel<LoginViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login_activity)

    btn_log_in.setOnClickListener { viewModel.logInCLicked(user_name_et.toText()) }

    viewModel.savedName.observe(this, Observer {user_name_et.setText(it)})
    viewModel.login.observe(this, Observer {
      startActivity(Intent(this, MenuActivity::class.java))
      finish()
    })
    viewModel.error.observe(this, Observer { user_name_til.error = getString(R.string.error_empty) })
  }
}
