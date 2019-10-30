package com.clean.cut.quiztactoe.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.clean.cut.quiztactoe.objects.Cell


class GameBoardGridAdapter : BaseAdapter() {
    private lateinit var list: ArrayList<String?>

    fun setList(list: Array<Array<Cell?>>) {
        this.list = ArrayList(list.flatten().map { it?.player?.sign })
        this.notifyDataSetChanged()
        Log.v("primjer", "${this.list}")

    }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var convertView = view
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.context).inflate(com.clean.cut.quiztactoe.R.layout.item_board_cell, null)
        }
        (convertView as TextView).text = list[position]
        return convertView

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