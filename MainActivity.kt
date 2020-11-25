package com.example.helppls


import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import org.w3c.dom.Text
var listView: ListView? = null
var helperino: Helperino? = null
var arrayList: ArrayList<MainActivity.songs>? = null
var addToCart: ArrayList<MainActivity.songs>?= null
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        listView = findViewById(R.id.listahan)
        arrayList = ArrayList()
        arrayList = setData()
        addToCart = ArrayList()
        helperino = Helperino(applicationContext, arrayList!!)

        listView?.adapter = helperino
        registerForContextMenu(listView)



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.stuff_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.Add -> {
                val intent = Intent(this, Albums::class.java)
            }
            R.id.Play ->{
                val intent = Intent(this, Queue::class.java)
                startActivity(intent)
                intent.putExtra("queue", addToCart);
            }
        }



        return super.onOptionsItemSelected(item)


    }


    private fun setData(): ArrayList<songs> {
        val arrayList: ArrayList<songs> = ArrayList()
        arrayList.add(songs("Help me plas", "Dead student"))
        arrayList.add(songs("Pogi ako", "Dead student"))
        arrayList.add(songs("A cry for help", "Dead student"))
        arrayList.add(songs("Student's Anguish in C minor", "Dead student"))
        arrayList.add(songs("Some Random Anime Song", "Dead student"))
        arrayList.add(songs("Why do you even bother", "Dead student"))
        arrayList.add(songs("Nope nope nope", "Dead student"))
        arrayList.add(songs("Skyrim Belongs to the Nords", "Dead student"))
        arrayList.add(songs("Papasa tayo promise", "Dead student"))
        arrayList.add(songs("Joke lang pre", "Dead student"))
        arrayList.add(songs("Help me pls", "Dead student"))
        arrayList.add(songs("Help me pls", "Dead student"))
        arrayList.add(songs("Help me pls", "Dead student"))
        arrayList.add(songs("Help me pls", "Dead student"))
        arrayList.add(songs("Help me pls", "Dead student"))
        arrayList.add(songs("Help me pls", "Dead student"))
        arrayList.add(songs("Help me pls", "Dead student"))
        arrayList.add(songs("Help me pls", "Dead student"))
        return arrayList!!
    }
    private fun addq(x: Int):ArrayList<songs>{
        val addToCart:ArrayList<songs> = ArrayList()
        addToCart.add(arrayList!![x])
        return addToCart
    }



    class songs {
        var title: String? = null
        var artist: String? = null

        constructor(title: String?, artist: String?) {
            this.title = title
            this.artist = artist
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu!!.setHeaderTitle("Select")
        menu.add(0, v!!.id, 0, "Add to queue")
        menu.add(9, v!!.id, 1, "Play")
        menu.add(9, v!!.id, 2, "View Queue")
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val selectedItemOrder = item!!.order
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position
        if (selectedItemOrder == 0)
        {
            var count = 0

                addToCart?.add(arrayList!![listPosition])
          
                count++
             val snackbar = Snackbar.make (findViewById(R.id.aLayout), "Song added to Queue", Snackbar.LENGTH_LONG)
            snackbar.setAction("Go to Queue", View.OnClickListener {
                val intent = Intent(this, Queue::class.java)
                startActivity(intent)
            })
            snackbar.show()

        }
        else if (selectedItemOrder == 2)
        {

        }

        return true
    }




}
