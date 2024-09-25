package com.example.bitmapbugsimulation

import android.app.Activity
import android.view.MotionEvent
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout

class WindowView(activity: Activity): FrameLayout(activity){
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return super.dispatchTouchEvent(ev)
    }
}