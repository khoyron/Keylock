package android.actionsheet.demo.com.khoiron.locklib.fingerpint

import android.actionsheet.demo.com.khoiron.locklib.fingerpint.FingerPrintActivity.contant.FINGER
import android.actionsheet.demo.com.khoiron.locklib.fingerpint.FingerPrintActivity.contant.FIRST
import android.actionsheet.demo.com.khoiron.locklib.fingerpint.FingerPrintActivity.contant.NOTCANCELLED
import android.app.Activity
import android.content.Context
import android.content.Intent

class FingerprintShow{

    lateinit var context : Context
    val finger by lazy { FingerprintShow(context) }

    fun fristFinger(frist:Boolean):FingerprintShow{
        mData.first = frist
        return finger
    }

    fun cancelledFinger(canceled:Boolean):FingerprintShow{
        mData.canceled = canceled
        return finger
    }

    constructor(context: Context)  {
        this.context = context
    }

    fun showPinlib(){
        var intent = Intent(context, FingerPrintActivity::class.java)
        intent.putExtra(FIRST, mData.first)
        intent.putExtra(NOTCANCELLED,mData.canceled)
        (context as Activity).startActivityForResult(intent,FINGER)
    }

    object mData{
        var first = true
        var canceled = true
    }

}