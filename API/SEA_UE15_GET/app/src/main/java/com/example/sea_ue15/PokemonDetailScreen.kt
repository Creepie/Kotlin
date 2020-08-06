package com.example.sea_ue15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pokemon_detail.*

class PokemonDetailScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        tV_name.text = "name: ${Singleton.name}"
        tV_height.text = "height: ${Singleton.height}"
        tV_id.text = "id: ${Singleton.id}"
    }
}
