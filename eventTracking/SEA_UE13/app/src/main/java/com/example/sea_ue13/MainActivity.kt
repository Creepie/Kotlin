package com.example.sea_ue13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener  {

    private lateinit var detector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        detector = GestureDetectorCompat(this, this) // context and listener
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i("TAG", "onTouchEvent")
        return if (detector.onTouchEvent(event)) { true } else { super.onTouchEvent(event) }
    }

    override fun onShowPress(e: MotionEvent?) {
        Log.i("TAG", "onShowPress")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Log.i("TAG", "onSingleTap")
        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
        Log.i("TAG", "onDown")
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        Log.i("TAG", "onFling")
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        Log.i("TAG", "onScroll")
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        Log.i("TAG", "onLongPress")
    }
}
