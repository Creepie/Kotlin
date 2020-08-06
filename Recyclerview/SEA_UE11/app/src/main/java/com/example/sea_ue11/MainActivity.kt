package com.example.sea_ue11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = arrayListOf<MyData>()
        list.add(MyData("AT", "austria"))
        list.add(MyData("IT", "Italien"))
        list.add(MyData("FR", "Frankreich"))
        list.add(MyData("DE", "Deutschland"))
        list.add(MyData("PT", "Portugal"))


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRecyclerAdapter(list)
    }
}

data class MyData(val countryCode: String, val country: String)

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){

    val bT_more = view.findViewById<Button>(R.id.bT_more)
    val tV_Header = view.findViewById<TextView>(R.id.tV_Header)
    val tv_Sub = view.findViewById<TextView>(R.id.tV_Sub)

     init {

     }
}

class MyRecyclerAdapter(val list: MutableList<MyData>) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_item,
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = list[position]
        holder.tV_Header.text = item.countryCode
        holder.tv_Sub.text = item.country

        //get a Toast message with the position of the clicked more button
        holder.bT_more.setOnClickListener {
            Toast.makeText(
                it.context, "Position im Recycler: ${position}", Toast.LENGTH_SHORT
            ).show()

        }

        //get a Toast message with the the country text > if you clicked on the item
        holder.itemView.setOnClickListener{
         Toast.makeText(
             it.context,"${item.countryCode} , ${item.country}", Toast.LENGTH_SHORT
         ).show()
        }
    }

}