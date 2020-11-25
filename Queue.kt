package com.example.helppls

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color.GREEN
import android.graphics.Color.parseColor
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Queue : AppCompatActivity() {

    private var MedyoAdapter: medyoAdapter? = null
    private var arrayLists: ArrayList<MainActivity.songs>? = null
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var notibuilder: Notification.Builder
    private val channelId = "package com.example.helppls"
    private val description = "Notifies when song Queue is empty"


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queue)



        val listViews = findViewById<ListView>(R.id.listt)
        arrayLists = ArrayList()

        MedyoAdapter = medyoAdapter(applicationContext, addToCart!!)
        listViews?.adapter = MedyoAdapter
        registerForContextMenu(listViews)








    }



    private class medyoAdapter (context: Context, var arrayLists: ArrayList<MainActivity.songs>) : BaseAdapter() {
        val mContext: Context = context
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
            val view: View = View.inflate(mContext, R.layout.helplayout, null)
            var titleText: TextView = view.findViewById(R.id.mercyTitle)
            var songList: MainActivity.songs = arrayLists.get(position)

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

        var counts = 0

        val builder:AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Delete from Queue")
        builder.setMessage("Are you sure you would like to remove this song from the queue?")
        builder.setPositiveButton("Yes" ,{dialog, which ->
            addToCart?.removeAt(listPosition)
            MedyoAdapter?.notifyDataSetChanged()
            Toast.makeText(this, "Song removed", Toast.LENGTH_SHORT).show()
            count--
            check()
            dialog.dismiss()})
        builder.setNegativeButton("No" ,{dialog, which -> dialog.dismiss()})
        builder.setNeutralButton("Cancel", {dialog, which -> dialog.dismiss() })

        val alertDialog:AlertDialog = builder.create()
        alertDialog.show()


        counts ++


    }
    else if (selectedItemOrder == 1)
    {

    }

    return true
}
    fun check()
    {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (count == 0)
        {
            val intent = Intent(this, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                notificationChannel= NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = parseColor("#00ff00")
                notificationChannel.enableVibration(false)
                notificationManager.createNotificationChannel(notificationChannel)
                notibuilder = Notification.Builder(this,channelId)
                        .setContentTitle("Empty Queue")
                        .setContentText("Song Queue has no songs stored")
                        .setSmallIcon(R.drawable.ic_launcher_round)
                        // .setLargeIcon(BitmapFactory.decodeResource(this, resources, R.drawable.ic_launcher))
                        .setContentIntent(pendingIntent)

            }
            else {
                notibuilder = Notification.Builder(this)
                        .setContentTitle("Empty Queue")
                        .setContentText("Song Queue has no songs stored")
                        .setSmallIcon(R.drawable.ic_launcher_round)
                        //  .setLargeIcon(BitmapFactory.decodeResource(this,resources, R.drawable.ic_launcher))
                        .setContentIntent(pendingIntent)
            }
            notificationManager.notify(1324, notibuilder.build())



        }
    }

}
