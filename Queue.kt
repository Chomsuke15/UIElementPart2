package com.example.helppls

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Queue : AppCompatActivity() {
    private var listView: ListView? = null
    private var MedyoAdapter: medyoAdapter? = null
    private var arrayList: ArrayList<MainActivity.songs>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queue)



        val listView = findViewById<ListView>(R.id.listt)
        arrayList = ArrayList()

        MedyoAdapter = medyoAdapter(applicationContext, addToCart!!)
        listView?.adapter = MedyoAdapter


    }



    private class medyoAdapter (context: Context, var arrayList: ArrayList<MainActivity.songs>) : BaseAdapter() {
        val mContext: Context = context
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
            var songList: MainActivity.songs = arrayList.get(position)

            titleText.text = songList.title
            return view!!
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.stuff_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Add -> {
                val intent = Intent(this, Albums::class.java)
                startActivity(intent)
            }
            R.id.Play -> {
                val intent = Intent(this, Queue::class.java)
                startActivity(intent)

            }
        }
   return true
    }

}