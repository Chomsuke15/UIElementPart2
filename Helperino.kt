package com.example.helppls

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class Helperino(context: Context, var arrayList: ArrayList<MainActivity.songs>) : BaseAdapter(){

     val
            mContext: Context

        init{
        mContext = context
    }
    override fun getItem(position: Int): Any {
        return arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = View.inflate(mContext, R.layout.helplayout, null)
        var titleText: TextView = view.findViewById(R.id.mercyTitle)
        var artistText: TextView = view.findViewById(R.id.SongArtist)
        var songList: MainActivity.songs = arrayList.get(position)

        titleText.text = songList.title
        artistText.text = songList.artist


        return view!!
    }








}