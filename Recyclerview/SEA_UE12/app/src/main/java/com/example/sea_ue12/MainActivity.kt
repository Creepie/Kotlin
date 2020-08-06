package com.example.sea_ue12

import android.content.Intent
import android.icu.text.CaseMap
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
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity() {
    val list = arrayListOf<RecylerItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //add Recycler Items
        list.add(AddData())
        list.add(Divider("Inspektionsfehler"))
        list.add(MyData("VZC", "Schlackenspritzer"))
        list.add(MyData("SAO", "Ofenschale"))
        list.add(Divider("meine Fehler"))
        list.add(MyData("KRU", "Kante Rau"))
        list.add(Divider("add more"))
        list.add(AddData())

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(list)
    }
}

/*
Recycler Adapter > handle all my Views
InsertListener > Interface to add objects
MyBaseHolder > abstract class
 */
class RecyclerAdapter(val data: MutableList<RecylerItem>):
    RecyclerView.Adapter<MyBaseHolder>(), InsertListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBaseHolder {
        return when(viewType){
            RecylerItem.TYPE_DATA -> DataViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    viewType,
                    parent,
                    false
                )
            )
            RecylerItem.TYPE_ADD -> AddViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    viewType,
                    parent,
                    false
                ), this
            )
            RecylerItem.TYPE_DIVIDER -> DividerViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    viewType,
                    parent,
                    false
                )
            )
            else -> throw IllegalStateException("Unsupported viewType $viewType")
        }
    }

    //
    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    override fun onBindViewHolder(holder: MyBaseHolder, position: Int) {
        holder.bind(data[position])
    }

    //fun to add a new item into the recycler
    //notifyItemInserted = to update the recycler after inserted a new item
    override fun insert(item: RecylerItem, position: Int) {
        data.add(position, item)
        notifyItemInserted(position)
    }
}

//create a interface > can create a List of RecylerItems
interface RecylerItem{
    val title: String
    val subTitle: String?
    val type: Int

    companion object{
        const val TYPE_DATA = R.layout.recycler_item_data
        const val TYPE_ADD = R.layout.recycler_item_add
        const val TYPE_DIVIDER = R.layout.recycler_item_divider
    }
}

interface InsertListener{
    fun insert(item: RecylerItem, position: Int)
}

//class for AddData (button to add more MyData objects)
class AddData(): RecylerItem{
    override val title: String
        get() = "add more"

    override val subTitle: String? = null

    override val type: Int
        get() = RecylerItem.TYPE_ADD
}

//data Class for MyData (create a reycler_item_data)
data class MyData(override val title: String, override val subTitle: String? = "Subtitle"):
    RecylerItem {
    override val type: Int
    get()= RecylerItem.TYPE_DATA
}

//data class for Divider (create a recycler_item_divider object)
data class Divider(override val title: String, override val subTitle: String? = null):
        RecylerItem {
    override val type: Int
    get() = RecylerItem.TYPE_DIVIDER
}

//abstract class with the fun bind
abstract class MyBaseHolder(view: View): RecyclerView.ViewHolder(view){
    abstract fun bind(item: RecylerItem)
}

//View Holder for a recycler_item_data object
class DataViewHolder(view: View): MyBaseHolder(view){

    //get the text views and the button of recycler_item_data
    private val tV_data_header = view.findViewById<TextView>(R.id.tV_data_header)
    private val tV_data_sub = view.findViewById<TextView>(R.id.tV_data_sub)
    private val button = view.findViewById<Button>(R.id.bT_data_more)

    override fun bind(item: RecylerItem) {
        //set the text of the textViews
        tV_data_header.text = item.title
        tV_data_sub.text = item.subTitle
        button.text = "more"

        itemView.setOnClickListener {
            Toast.makeText(it.context, "Item $adapterPosition clicked", Toast.LENGTH_SHORT)
                .show()
        }

        button.setOnClickListener {
            Toast.makeText(it.context, "Data Button $adapterPosition clicked", Toast.LENGTH_SHORT)
                .show()

            //start the MainActivity again > push on the stack
            val i = Intent(button.context, MainActivity::class.java)
            button.context.startActivity(i)
        }
    }
}

//View Holder for a recycler_item_add object
class AddViewHolder(view: View, private val insertListener: InsertListener): MyBaseHolder(view){

    //get the button of recycler_item_add xml
    private val button = view.findViewById<Button>(R.id.add_addMore)

    override fun bind(item: RecylerItem) {
        //set the text of the button to object title
        button.text = item.title

        //if button clicked > add new MyData object
        button.setOnClickListener {
            insertListener.insert(MyData("New Data $adapterPosition"), adapterPosition)
        }
    }
}

//View Holder for a recycler_item_divider object
class DividerViewHolder(view: View): MyBaseHolder(view){
    private val tvTitle = view.findViewById<TextView>(R.id.tV_divider_Header)

    override fun bind(item: RecylerItem) {
        tvTitle.text = item.title
    }
}









