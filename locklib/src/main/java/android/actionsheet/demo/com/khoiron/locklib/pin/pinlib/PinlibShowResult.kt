package android.actionsheet.demo.com.khoiron.locklib.pin.pinlib

import android.actionsheet.demo.com.khoiron.locklib.pin.pinlib.Pinlib.mData.PINSHOW
import android.app.Activity
import android.content.Intent

class PinlibShowResult{

    var requestCode = 0
    var resultCode = 0
    var data: Intent
//    val dataResult by lazy { PinlibShowResult(requestCode, resultCode, data) }

    constructor(requestCode: Int, resultCode: Int, data: Intent?,callback: callback)  {
        this.requestCode = requestCode
        this.resultCode = resultCode
        this.data = data!!
        if (requestCode==PINSHOW){
            if (resultCode == Activity.RESULT_OK) {
                callback.data(data!!)
            }
        }
    }
/*
    fun resultPinlib(callback: callback){

    }*/

    interface callback{
        fun data(data:Intent)
    }
}