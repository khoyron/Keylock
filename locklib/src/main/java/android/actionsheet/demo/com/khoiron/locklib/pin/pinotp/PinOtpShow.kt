package android.actionsheet.demo.com.khoiron.locklib.pin.pinotp

import android.app.Activity
import android.content.Context
import android.content.Intent

class PinOtpShow {

    lateinit var context: Context
    val pinlibShow by lazy { PinOtpShow(context) }

    constructor(context: Context) {
        this.context = context
    }

    fun setCode(code: String): PinOtpShow {
        mData.code = code
        return pinlibShow
    }

    fun setPhone(phone: String): PinOtpShow {
        mData.phone = phone
        return pinlibShow
    }

    fun setMessage(message: String): PinOtpShow {
        mData.message = message
        return pinlibShow
    }

    fun setType(first: Boolean): PinOtpShow {
        mData.firs = first
        return pinlibShow
    }

    fun setLink(link: String): PinOtpShow {
        mData.link = link
        return pinlibShow
    }

    fun setTypeCancell(cancelled: Boolean): PinOtpShow {
        mData.cancelled = cancelled
        return pinlibShow
    }

    fun showPinOtp() {
        var int = Intent()
        int.putExtra(PinOtp.mData.FIRST, mData.firs)
        int.putExtra(PinOtp.mData.NOTCANCELLED, mData.cancelled)
        int.putExtra(PinOtp.mData.NO_PHONE, mData.phone)
        int.putExtra(PinOtp.mData.CODE, mData.code)
        int.putExtra(PinOtp.mData.TITLE, mData.message)
        int.putExtra(PinOtp.mData.URL_IMAGE, mData.link)
        (context as Activity).startActivityForResult(int, PinOtp.mData.PINSHOW)

    }

    object mData {
        var code = ""
        var phone = ""
        var message = ""
        var link = ""
        var firs = false
        var cancelled = false
    }
}