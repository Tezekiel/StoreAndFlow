package com.clean.cut.quiztactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.ListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_local_game.*

class LocalGameActivity : AppCompatActivity() {
    private lateinit var adapter: Adapter
    var list = arrayListOf("a", "b","c", "d")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_local_game)

        initialize()
    }

    private fun initialize() {
        
    }
}
