package com.example.qrcodescan

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bT_scan.setOnClickListener {
            initScan()
        }
    }

    private fun initScan(){
        IntentIntegrator(this).initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null){
            if (result.contents == null){
                Toast.makeText(this, "Der QR-Code ist leer", Toast.LENGTH_SHORT).show()
            } else {
                val text = result.contents.toString()
                if (text.contains("http")){
                    val openURL = Intent(android.content.Intent.ACTION_VIEW)
                    openURL.data = Uri.parse(text)
                    startActivity(openURL)
                }else{
                    tV_result.text = text
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}