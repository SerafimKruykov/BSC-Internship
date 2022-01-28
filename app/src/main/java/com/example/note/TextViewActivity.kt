package com.example.note

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.note.Constants.Animation

class TextViewActivity : AppCompatActivity() {

    private lateinit var textView: CustomTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view)

        textView = findViewById(R.id.customTextView)
        textView.setOnClickListener {
            animate(it)
        }
    }

    private fun animate(view: View){
        ObjectAnimator.ofFloat(
            view,
            View.ROTATION_X,
            Animation.START,
            Animation.FINISH
        ).apply {
            duration = Animation.DURATION
            repeatCount = Animation.REPEAT_COUNT
            start()
        }
    }
}