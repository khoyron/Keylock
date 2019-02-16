package android.actionsheet.demo.com.khoiron.locklib.fingerpint

import android.Manifest
import android.actionsheet.demo.com.khoiron.locklib.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.CancellationSignal
import android.preference.PreferenceManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.TextView
import android.widget.Toast

/**
 * Created by khoiron on 14/06/18.
 */

class FingerprintHandler : FingerprintManager.AuthenticationCallback {

    var context: Context
    var boolean = false

    // Constructor
    constructor(mContext: Context, boolean: Boolean) {
        context = mContext
        this.boolean = boolean
    }

    fun startAuth(manager: FingerprintManager, cryptoObject: FingerprintManager.CryptoObject) {
        val cancellationSignal = CancellationSignal()
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            manager.authenticate(cryptoObject, cancellationSignal, 0, this, null)
        }
    }


    override fun onAuthenticationError(errMsgId: Int, errString: CharSequence) {
        this.update("Fingerprint Authentication error\n$errString", false)
    }


    override fun onAuthenticationHelp(helpMsgId: Int, helpString: CharSequence) {
        this.update("Fingerprint Authentication help\n$helpString", false)
    }


    override fun onAuthenticationFailed() {
        this.update("Fingerprint Authentication failed.", false)
    }


    override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult) {
        this.update("Fingerprint Authentication succeeded.", true)
        setDataPreferenceBolean(context, "lock", true)
        if (boolean) {
            Toast.makeText(context, "Succes insert fingerprint", Toast.LENGTH_LONG).show()
        }
        val returnIntent = Intent()
        (context as Activity).setResult(Activity.RESULT_OK, returnIntent)
        (context as Activity).finish()
    }

    fun setDataPreferenceBolean(context: Context, key: String, value: Boolean?) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPref.edit()
        editor.putBoolean(key, value!!)
        editor.apply()
    }

    fun update(e: String, success: Boolean?) {
        val textView = (context as Activity).findViewById(R.id.errorText) as TextView
        textView.text = e
        if (success!!) {
            textView.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
        }
    }

}