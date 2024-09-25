package com.example.bitmapbugsimulation

import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import java.util.logging.Logger

private const val WINDOW_FLAGS_UNFOCUSED = (
        WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                or WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR
                or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
                or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)


class MainActivity : AppCompatActivity() {

    override fun onStart() {
        super.onStart()
        val layout = LinearLayout(this)
        layout.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.orientation = LinearLayout.VERTICAL
        layout.setBackgroundColor(Color.RED)

        val button = Button(this).run {
            setOnClickListener {
                createTransparentWebViewWindow(layout)
            }
            text = "test"
            this
        }


        layout.addView(button)

        setContentView(layout)
    }

    private fun createTransparentWebViewWindow(layout: LinearLayout){
        val params = WindowManager.LayoutParams(
            layout.width,
            layout.height,
            WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG,
            WINDOW_FLAGS_UNFOCUSED,
            PixelFormat.TRANSLUCENT
        )

        val location = IntArray(2)
        layout.getLocationOnScreen(location)

        params.gravity = Gravity.START or Gravity.TOP

        params.x = location[0]
        params.y = location[1]

        val windowView = FrameLayout(this)

        windowManager.addView(windowView, params)

        val webview = CustomWebView(this)
        webview.loadUrl("https://regal-caramel-d573d9.netlify.app")

        windowView.addView(webview)
    }
}
