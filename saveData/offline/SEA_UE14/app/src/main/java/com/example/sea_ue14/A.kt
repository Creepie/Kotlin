package com.example.sea_ue14

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_a.*
import kotlinx.android.synthetic.main.activity_main.*

class A : AppCompatActivity() {
    val prefName = "MyPref"
    val prefKey = "GetText"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)

        val prefs = getSharedPreferences(prefName, Context.MODE_PRIVATE)


        bT_A_save.setOnClickListener {
            val editor = prefs.edit()
            editor.putString(prefKey, eT_A_input.text.toString())
                .apply()
        }

        bT_A_load.setOnClickListener {
            val value = prefs.getString(prefKey,null)
            tV_A_text.text = value.toString()
        }
    }
}
