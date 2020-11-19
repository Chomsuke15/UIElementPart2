package com.example.helppls


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*

class mercy : AppCompatActivity() {
    var arrayLists: ArrayList<mercy.songs>? = null
    var helperinos: Helperinos? = null
    var listViews: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mercy)


        listViews = findViewById(R.id.helpme)
        arrayLists = ArrayList()
        arrayLists = setData()
        helperinos = Helperinos(applicationContext, arrayLists!!)

        listViews?.adapter = helperinos


    }
    private fun setData(): ArrayList<mercy.songs>? {
        val arrayList: ArrayList<songs> = ArrayList()
        arrayList.add(songs("Help me plas", "Dead student"))
        arrayList.add(songs("Pogi ako", "Dead student"))
        arrayList.add(songs("A cry for help", "Dead student"))
        arrayList.add(songs("Student's Anguish in C minor", "Dead student"))
        return arrayList!!
    }

    class songs {
        var title: String? = null
        var artist: String? = null

        constructor(title: String?, artist: String?) {
            this.title = title
            this.artist = artist
        }
    }



    class Helperinos(context: Context, var arrayLists: ArrayList<mercy.songs>) :
        BaseAdapter() {

        val
                mContext: Context

        init {
            mContext = context
        }

        override fun getItem(position: Int): Any {
            return arrayLists.get(position)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return arrayLists.size
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View = View.inflate(mContext, R.layout.helplayout2, null)
            var titleText: TextView = view.findViewById(R.id.mercyTitle)
            var artistText: TextView = view.findViewById(R.id.SongArtist)
            var songList: mercy.songs = arrayLists.get(position)

            titleText.text = songList.title
            artistText.text = songList.artist


            return view!!
        }
    }
}


