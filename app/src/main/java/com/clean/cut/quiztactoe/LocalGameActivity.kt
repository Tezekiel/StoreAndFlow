package com.clean.cut.quiztactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.clean.cut.quiztactoe.adapters.GameBoardGridAdapter
import com.clean.cut.quiztactoe.databinding.ActivityLocalGameBinding
import com.clean.cut.quiztactoe.objects.Cell
import com.clean.cut.quiztactoe.objects.Player
import com.clean.cut.quiztactoe.viewmodel.LocalGameViewModel
import kotlinx.android.synthetic.main.activity_local_game.*

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
        val binding: ActivityLocalGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_local_game)
        viewModel = ViewModelProviders.of(this).get(LocalGameViewModel::class.java)
        getDataFromIntent()

        viewModel.init(rowCount, columnCount, player1, player2)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //rv adapter
        adapter = GameBoardGridAdapter()
        adapter.setList(viewModel.cells.value!!)
        boardGv.numColumns = columnCount
        boardGv.adapter = adapter

        //observers
        viewModel.cells.observe(this, Observer {
            adapter.setList(it)
        })
        /*viewModel.getWinner().observe(this, Observer {
            Log.v("primjer", "WIN")
        })*/

    }

    private fun getDataFromIntent() {
        player1 = intent.getSerializableExtra("player1") as Player
        player2 = intent.getSerializableExtra("player2") as Player
        rowCount = intent.getIntExtra("rowCount", 3)
        columnCount = intent.getIntExtra("columnCount", 3)
    }
}
