package android.actionsheet.demo.com.khoiron.locklib.fingerpint

import android.actionsheet.demo.com.khoiron.locklib.fingerpint.FingerPrintActivity.contant.FINGER
import android.app.Activity
import android.content.Intent

class FingerprintResult{

    var requestCode = 0
    var resultCode = 0
    var data: Intent

    constructor(requestCode: Int, resultCode: Int, data: Intent?,callback: callback)  {
        this.requestCode = requestCode
        this.resultCode = resultCode
        this.data = data!!
        if (requestCode== FINGER){
            if (resultCode == Activity.RESULT_OK) {
                callback.data(data!!)
            }
        }
    }


    interface callback{
        fun data(data:Intent)
    }
}