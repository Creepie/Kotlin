package com.example.nfc_read

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.nfc.FormatException
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: NfcAdapter? = null
    var tag: WritableTag? = null
    var tagId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNfcAdapter()
    }

    private fun initNfcAdapter() {
        val nfcManager = getSystemService(Context.NFC_SERVICE) as NfcManager
        adapter = nfcManager.defaultAdapter
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        enableNfcForegroundDispatch()
    }

    override fun onPause() {
        disableNfcForegroundDispatch()
        super.onPause()
    }

    private fun enableNfcForegroundDispatch() {
        try {
            val intent = Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            val nfcPendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
            adapter?.enableForegroundDispatch(this, nfcPendingIntent, null, null)
        } catch (ex: IllegalStateException) {
            Log.e(getTag(), "Error enabling NFC foreground dispatch", ex)
        }
    }

    private fun disableNfcForegroundDispatch() {
        try {
            adapter?.disableForegroundDispatch(this)
        } catch (ex: IllegalStateException) {
            Log.e(getTag(), "Error disabling NFC foreground dispatch", ex)
        }
    }

    private fun getTag() = "MainActivity"

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val tagFromIntent = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
        try {
            tag = WritableTag(tagFromIntent)
        } catch (e: FormatException) {
            Log.e(getTag(), "Unsupported tag tapped", e)
            return
        }
        tagId = tag!!.tagId

        val result = StringBuilder()
        var i = 0
        while (i <= tagId?.length?.minus(2) ?: 0) {
            result.append(tagId?.substring(i, i + 2)?.let { StringBuilder(it).reverse() })
            i += 2
        }

        val employerNumber = result.reverse().toString()
        tag_uid.text = employerNumber
        showToast("Tag tapped: $employerNumber")

    }
}