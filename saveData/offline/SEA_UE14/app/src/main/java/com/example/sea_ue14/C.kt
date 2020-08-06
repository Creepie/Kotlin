package com.example.sea_ue14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_c.*

class C : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)

        bT_C_saveAndExit.setOnClickListener {
            (application as AppObject).textC = eT_C_text.text.toString()
            finish()
        }
    }
}
