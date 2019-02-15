package android.actionsheet.demo.com.khoiron.locklib.pin.pinotp

import android.actionsheet.demo.com.khoiron.locklib.pin.pinotp.PinOtp.mData.PINSHOW
import android.app.Activity
import android.content.Intent

class PinOtpShowResult{

    var requestCode = 0
    var resultCode = 0
    lateinit var data: Intent

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

    fun resultPinOtp(){

    }
*/

    interface callback{
        fun data(data:Intent)
    }
}