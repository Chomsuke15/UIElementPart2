package com.example.helppls


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar

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
        registerForContextMenu(listViews)

    }
    private fun setData(): ArrayList<mercy.songs>? {
        val arrayList: ArrayList<songs> = ArrayList()
        arrayList.add(songs("Help me plas", "Dead student"))
        arrayList.add(songs("Pogi ako", "Narcissistic Student"))
        arrayList.add(songs("A cry for help", "Crying student"))
        arrayList.add(songs("Student's Anguish in C minor", "Classical student"))
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
    override fun onCreateOptionsMenu(smenu: Menu?): Boolean {
        menuInflater.inflate(R.menu.second,smenu)
        return super.onCreateOptionsMenu(smenu)
    }
    override fun onCreateContextMenu(
            smenu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(smenu, v, menuInfo)
        smenu!!.setHeaderTitle("Delete?")
        smenu.add(0, v!!.id, 0, "Delete")
        smenu.add(0, v!!.id, 1, "Refresj")

    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val selectedItemOrder = item!!.order
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position
        if (selectedItemOrder == 0)
        {

            var count = 0

            val builder:AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle("Delete from album")
            builder.setMessage("Are you sure you would like to remove this song from this album?")
            builder.setPositiveButton("Yes" ,{dialog, which ->
                arrayLists?.removeAt(listPosition)
                helperinos?.notifyDataSetChanged()
                Toast.makeText(this, "Song removed", Toast.LENGTH_SHORT).show()
                dialog.dismiss()})
            builder.setNegativeButton("No" ,{dialog, which -> dialog.dismiss()})
            builder.setNeutralButton("Cancel", {dialog, which -> dialog.dismiss() })

            val alertDialog:AlertDialog = builder.create()
            alertDialog.show()

            count ++


        }
        else if (selectedItemOrder == 1)
        {
            helperinos?.notifyDataSetChanged()
        }

        return true
    }
}


