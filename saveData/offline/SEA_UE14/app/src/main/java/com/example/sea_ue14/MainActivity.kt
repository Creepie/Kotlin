package com.example.sea_ue14

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_a.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val prefName = "MyPref"
    val prefKey = "GetText"
    var textB = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences(prefName, Context.MODE_PRIVATE)

        bT_main_A.setOnClickListener {
            val i = Intent(this, A::class.java)
            startActivity(i)
        }

        bt_main_loadTextA.setOnClickListener {
            val value = prefs.getString(prefKey,null)
            tV_main_text.text = value.toString()
        }

        bt_main_B.setOnClickListener {
            val i = Intent(this, B::class.java)
            startActivityForResult(i,999)
        }

        bt_main_loadTextB.setOnClickListener {
            tV_main_text.text = textB
        }

        bt_main_D.setOnClickListener {
            val i = Intent(this, D::class.java)
            startActivity(i)
        }

        bt_main_loadTextD.setOnClickListener {
            tV_main_text.text = Singleton.textD
        }

        bt_main_C.setOnClickListener {
            val i = Intent(this, C::class.java)
            startActivity(i)
        }

        bt_main_loadTextC.setOnClickListener {
            tV_main_text.text = (application as AppObject).textC
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 999 && resultCode == Activity.RESULT_OK){
            textB = data?.getStringExtra("textB").toString()
        }
    }
}
