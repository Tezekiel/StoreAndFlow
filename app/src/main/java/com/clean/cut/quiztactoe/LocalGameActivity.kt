package com.clean.cut.quiztactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.clean.cut.quiztactoe.adapters.GameBoardGridAdapter
import kotlinx.android.synthetic.main.activity_local_game.*

class LocalGameActivity : AppCompatActivity() {
    private lateinit var adapter: GameBoardGridAdapter
    var list = arrayListOf("a", "b","c", "d")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_game)

        initialize()
    }

    private fun initialize() {


        adapter = GameBoardGridAdapter(list)
        boardGv.numColumns = 3
        boardGv.adapter = adapter
    }
}
