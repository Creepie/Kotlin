package com.example.sea_ue14

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_b.*

class B : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        bT_b_saveAndFinish.setOnClickListener {
            val i = intent
            i.putExtra("textB", eT_b_text.text.toString())
            setResult(Activity.RESULT_OK, i)
            finish()
        }
    }
}
