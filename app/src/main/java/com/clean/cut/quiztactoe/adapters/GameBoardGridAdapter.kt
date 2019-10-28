package com.clean.cut.quiztactoe.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.clean.cut.quiztactoe.R
import com.clean.cut.quiztactoe.util.inflate

class GameBoardGridAdapter(private var list:ArrayList<String>): BaseAdapter() {

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        return p2?.inflate(R.layout.item_board_cell, false)!!
    }

    override fun getItem(p0: Int): Any {
        return ""
    }

    override fun getItemId(p0: Int): Long {
        return 0L
    }

    override fun getCount(): Int {
        return list.size
    }



}