package com.example.sea_ue15

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

class MainActivity : AppCompatActivity() {

    private var BASE_URL = "https://pokeapi.co/api/v2/"
    private var next = "pokemon?limit=100&offset=0"

    var list = arrayListOf<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MyRecyclerAdapter(list,this)

        apiCall()

    }

    fun apiCall(){
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(PokemonAPI::class.java)
        service.loadPokemons(next).enqueue(object: Callback<PokemonResponse>{
            override fun onResponse(call: Call<PokemonResponse>, response: Response<PokemonResponse>) {
                if(response.isSuccessful){
                    Log.e("Tag", "alles gucci")
                    val data = response.body()

                    if (data != null) {
                        for (item in data.pokemons){
                            list.add(item)
                        }
                        recyclerView.adapter?.notifyDataSetChanged()
                    }

                    if (data != null) {
                        next = data.next.toString()
                    }
                    if (next != "null"){
                        Log.i("LOG", "once again, we need more Pokemons")
                        apiCall()
                    } else {
                        Log.i("LOG", "no more Pokemons from API")
                    }
                }
            }
            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Log.e("Tag", "error")
            }
        })
    }


}



class PokemonResponse(val next: String?,
                      @SerializedName("results")
                      val pokemons: List<Pokemon>)

class PokemonDetailResponse(val height: Int, val id: Int)

class Pokemon(val name: String, val url: String){
}

interface PokemonAPI {
    @GET
    fun loadPokemons(@Url url: String): Call<PokemonResponse>

    @GET
    fun loadDetailPokemon(@Url url: String): Call<PokemonDetailResponse>
}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){

    val tV_name = view.findViewById<TextView>(R.id.recycler_name)
    val tV_url = view.findViewById<TextView>(R.id.recycler_url)

    init {

    }
}

class MyRecyclerAdapter(val list: ArrayList<Pokemon>, val context: Context) : RecyclerView.Adapter<MyViewHolder>() {
    private var BASE_URL = "https://pokeapi.co/api/v2/"
    private var detail = "pokemon/208/"

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
        holder.tV_name.text = item.name
        holder.tV_url.text = item.url

        //get a Toast message with the the country text > if you clicked on the item
        holder.itemView.setOnClickListener{
            detail = holder.tV_url.text.toString()
            Singleton.name = item.name
            apiDetailCall()
            Toast.makeText(
                it.context,"${item.name} , ${item.url}", Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun apiDetailCall(){
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(PokemonAPI::class.java)
        service.loadDetailPokemon(detail).enqueue(object: Callback<PokemonDetailResponse>{
            override fun onResponse(call: Call<PokemonDetailResponse>, response: Response<PokemonDetailResponse>) {
                if(response.isSuccessful){
                    Log.e("Tag", "alles gucci")
                    val data = response.body()
                    println()
                    if (data != null) {
                        Singleton.height = data.height
                        Singleton.id = data.id
                    }
                    val intent = Intent(context, PokemonDetailScreen::class.java)
                    context.startActivity(intent)
                }
            }
            override fun onFailure(call: Call<PokemonDetailResponse>, t: Throwable) {
                Log.e("Tag", "error")
            }
        })
    }

}



