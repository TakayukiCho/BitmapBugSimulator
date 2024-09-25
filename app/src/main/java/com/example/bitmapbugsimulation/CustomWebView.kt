package com.example.bitmapbugsimulation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.MotionEvent
import android.webkit.WebView


class CustomWebView(context: Context) : WebView(context.applicationContext) {
    init {
        settings.javaScriptEnabled = true
        @Suppress("DEPRECATION")
        settings.savePassword = false
        settings.domStorageEnabled = true
        settings.databaseEnabled = true

        setBackgroundColor(Color.TRANSPARENT)

        // 初回表示時にスクロールバーが画面端にちらつく現象の回避
        isVerticalScrollBarEnabled = false
        isHorizontalScrollBarEnabled = false
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ALPHA_8)

        val canvas = Canvas(bitmap)

        draw(canvas)

        return super.dispatchTouchEvent(ev)
    }

}