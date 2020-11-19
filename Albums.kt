package com.example.helppls

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.GridView

class Albums : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var gridView: GridView? = null
    private var arrayList: ArrayList<albumlist>? = null
    private var adapter: Adapterspo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        gridView = findViewById(R.id.myView)
        arrayList = ArrayList()
        arrayList = setdata()
        adapter = Adapterspo(applicationContext, arrayList!!)
        gridView?.adapter = adapter
        gridView?.onItemClickListener = this



    }

    private fun setdata(): ArrayList<albumlist> {

        var arr: ArrayList<albumlist> = ArrayList()
        arr.add(albumlist(R.drawable.b, "Symphony of Requirement"))
        arr.add(albumlist(R.drawable.c, "Requiem of School"))

        return arr
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var Albumlist:albumlist = arrayList!!.get(position)
        val intent = Intent(this, mercy::class.java)
        startActivity(intent)
    }

}