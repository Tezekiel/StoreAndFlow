package com.clean.cut.quiztactoe.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.clean.cut.quiztactoe.R
import com.clean.cut.quiztactoe.objects.Player
import kotlinx.android.synthetic.main.activity_local_game_setup.*

class LocalGameSetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_game_setup)
    }

    fun startActivityLocalGame(view: View) {
        if(!checkForInputErrors()){
            val intent = Intent(this, LocalGameActivity::class.java)
            intent.putExtra("player1", Player(player1NameEt.text.toString(), "x"))
            intent.putExtra("player2", Player(player2NameEt.text.toString(), "o"))
            intent.putExtra("rowCount", rowNumberEt.text.toString().toInt())
            intent.putExtra("columnCount", columnNumberEt.text.toString().toInt())
            startActivity(intent)
        }
    }

    private fun checkForInputErrors(): Boolean {
        var isThereError = false
        if(player1NameEt.text.isNullOrEmpty()){
            player1NameIL.error = "Name can't be empty"
            isThereError = true
        }else{
            player1NameIL.error = null
        }

        if(player2NameEt.text.isNullOrEmpty()) {
            player2NameIL.error = "Name can't be empty"
            isThereError = true
        }else{
            player2NameIL.error = null
        }

        when {
            rowNumberEt.text.isNullOrEmpty() -> {
                rowNumberIL.error = "Row number can't be empty"
                isThereError = true
            }
            (rowNumberEt.text.toString().toInt()) !in 3..6 -> {
                rowNumberIL.error = "Number must be beetween 3 and 6"
                isThereError = true
            }
            else -> rowNumberIL.error = null
        }
        
        when {
            columnNumberEt.text.isNullOrEmpty() -> {
                columnNumberIL.error = "Column number can't be empty"
                isThereError = true
            }
            (columnNumberEt.text.toString().toInt()) !in 3..6 -> {
                columnNumberIL.error = "Number must be beetween 3 and 6"
                isThereError = true
            }
            else -> columnNumberIL.error = null
        }

        return isThereError

    }
}
