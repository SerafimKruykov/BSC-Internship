package com.example.note

import android.content.Context
import android.text.Html
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatTextView

class CustomTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    var htmlText: String? = null
        set(value) {
            field = value
            text = value?.let { Html.fromHtml(value) }
        }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomTextView,
            defStyleAttr,
            0
        ).also { tipedArray ->
            htmlText = tipedArray.getString(R.styleable.CustomTextView_htmlText)
        }.recycle()
    }

}