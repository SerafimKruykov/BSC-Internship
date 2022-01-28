package com.example.note

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webView = findViewById(R.id.webView)

        webView.apply {
            settings.javaScriptEnabled = true
            settings.loadsImagesAutomatically = true
            webViewClient = WebViewClient()
            loadUrl(context.getString(R.string.webViewUrl))
        }
    }

    override fun onBackPressed() {
        if(webView.canGoBack())webView.goBack() else super.onBackPressed()
    }
}