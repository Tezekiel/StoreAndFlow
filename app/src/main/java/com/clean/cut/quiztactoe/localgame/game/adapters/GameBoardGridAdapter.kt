package com.clean.cut.quiztactoe.localgame.game.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import com.clean.cut.quiztactoe.model.Cell

class GameBoardGridAdapter : BaseAdapter() {
  var data: List<Cell> = emptyList()
    set(value) {
      field = value
      Log.v("primjer", "data $data")
      notifyDataSetChanged()
    }

  var listener: (Cell, Int) -> Unit = { _: Cell, _: Int -> }

  @SuppressLint("ViewHolder")
  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    val view = LayoutInflater.from(parent.context).inflate(com.clean.cut.quiztactoe.R.layout.item_board_cell, null)
    (view as TextView).text = data[position].state

    view.layoutParams = ViewGroup.LayoutParams(
      parent.width / (parent as GridView).numColumns,
      parent.height / (data.size / parent.numColumns)
    )

    view.setOnClickListener { listener(data[position], position) }

    return view
  }

  override fun getItem(p0: Int): Any {
    return data[p0]
  }

  override fun getItemId(p0: Int): Long {
    return 0L
  }

  override fun getCount(): Int {
    return data.size
  }


}