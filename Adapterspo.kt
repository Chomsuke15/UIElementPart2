package com.example.helppls

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class Adapterspo(var context: Context, var arrayList: ArrayList<albumlist>): BaseAdapter(){


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

        var view:View = View.inflate(context, R.layout.gridviewplc, null)

        var imags: ImageView = view.findViewById(R.id.a)
        var name:TextView=view.findViewById(R.id.asas)

        var Albumlist: albumlist = arrayList.get(position)

        imags.setImageResource(Albumlist.imgs!!)
        name.text = Albumlist.name

        return view
    }

}