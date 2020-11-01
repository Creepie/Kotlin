package com.example.logintestproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {

    private var clickNum = 0
    private lateinit var textView : TextView
    private lateinit var analytics : FirebaseAnalytics

    @SuppressLint("SetTextI18n", "InvalidAnalyticsName")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.myText)
        analytics = FirebaseAnalytics.getInstance(this) // google anatytics for button

        val upButton = findViewById<Button>(R.id.button)
        upButton.setOnClickListener {
            clickNum++;
            textView.text = "Value is $clickNum"
            analytics.logEvent("button clicked", null)
        }

    }



}