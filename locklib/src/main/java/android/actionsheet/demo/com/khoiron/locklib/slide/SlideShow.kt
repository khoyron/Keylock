package android.actionsheet.demo.com.khoiron.locklib.slide

import android.app.Activity
import android.content.Context
import android.content.Intent

class SlideShow {

    lateinit var context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun slideShow() {
        var intent = Intent(context, ActivitySlider::class.java)
        (context as Activity).startActivity(intent)
    }

}