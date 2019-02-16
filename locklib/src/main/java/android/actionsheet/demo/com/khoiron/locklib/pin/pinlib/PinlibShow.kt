package android.actionsheet.demo.com.khoiron.locklib.pin.pinlib

import android.actionsheet.demo.com.khoiron.locklib.pin.pinlib.Pinlib.mData.PINSHOW
import android.app.Activity
import android.content.Context
import android.content.Intent

class PinlibShow {

    lateinit var context: Context
    val pinlibShow by lazy { PinlibShow(context) }

    constructor(context: Context) {
        this.context = context
    }

    fun setPin(pin: String): PinlibShow {
        mData.pin = pin
        return pinlibShow
    }

    fun setUrl(url: String): PinlibShow {
        mData.urlImage = url
        return pinlibShow
    }

    fun setType(first: Boolean): PinlibShow {
        mData.type = first
        return pinlibShow
    }

    fun setNotCancelled(cancelled: Boolean): PinlibShow {
        mData.cancelled = cancelled
        return pinlibShow
    }

    fun showPinlib() {
        var int = Intent(context, Pinlib::class.java)
        int.putExtra(Pinlib.mData.FIRST, mData.type)
        int.putExtra(Pinlib.mData.NOTCANCELLED, mData.cancelled)
        int.putExtra(Pinlib.mData.PIN, mData.pin)
        int.putExtra(Pinlib.mData.URL_IMAGE, mData.urlImage)
        (context as Activity).startActivityForResult(int, PINSHOW)
    }

    object mData {
        var type = false
        var cancelled = false
        var pin: String = ""
        var urlImage: String = ""
    }

}