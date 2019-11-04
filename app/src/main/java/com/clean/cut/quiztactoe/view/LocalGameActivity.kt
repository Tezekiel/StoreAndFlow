package com.clean.cut.quiztactoe.view

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.clean.cut.quiztactoe.adapters.GameBoardGridAdapter
import com.clean.cut.quiztactoe.databinding.ActivityLocalGameBinding
import com.clean.cut.quiztactoe.objects.Player
import com.clean.cut.quiztactoe.viewmodel.LocalGameViewModel
import kotlinx.android.synthetic.main.activity_local_game.*
import kotlin.math.min


class LocalGameActivity : AppCompatActivity() {
    private lateinit var viewModel: LocalGameViewModel
    private lateinit var adapter: GameBoardGridAdapter
    lateinit var player1: Player
    lateinit var player2: Player
    var rowCount: Int = 0
    var columnCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialize()
    }

    private fun initialize() {
        //boilerplate
        val binding: ActivityLocalGameBinding = DataBindingUtil.setContentView(this,
            com.clean.cut.quiztactoe.R.layout.activity_local_game
        )
        viewModel = ViewModelProviders.of(this).get(LocalGameViewModel::class.java)

        //get data from previous activity
        getDataFromIntent()

        //architecture stuff
        viewModel.init(rowCount, columnCount, player1, player2)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //gv adapter
        adapter = GameBoardGridAdapter()
        adapter.setList(viewModel.cells.value!!)

        //gv looks and adapter
        val gvParams = boardGv.layoutParams
        gvParams.width = getDeviceWidth() - 50
        gvParams.height = gvParams.width
        boardGv.layoutParams = gvParams
        boardGv.numColumns = columnCount

        boardGv.adapter = adapter

        //observers
        viewModel.cells.observe(this, Observer {
            adapter.setList(it)
        })
    }

    private fun getDataFromIntent() {
        player1 = intent.getSerializableExtra("player1") as Player
        player2 = intent.getSerializableExtra("player2") as Player
        rowCount = intent.getIntExtra("rowCount", 3)
        columnCount = intent.getIntExtra("columnCount", 3)
    }

    private fun getDeviceWidth(): Int {
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)

        val widthPixels = metrics.widthPixels
        val heightPixels = metrics.heightPixels

        return min(widthPixels, heightPixels)

    }
}
