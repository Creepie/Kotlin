package com.example.sea_ue14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_b.*
import kotlinx.android.synthetic.main.activity_d.*

class D : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d)

        bT_D_saveAndExit.setOnClickListener {
            Singleton.textD = eT_D_text.text.toString()
            finish()
        }
    }
}
