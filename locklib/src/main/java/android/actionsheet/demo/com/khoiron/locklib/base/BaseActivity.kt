package android.actionsheet.demo.com.khoiron.locklib.base

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

/**
 * Created by khoiron on 14/06/18.
 */
abstract class BaseActivity : AppCompatActivity() {

    lateinit var pDialog: ProgressDialog

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        pDialog = ProgressDialog(this)
        pDialog.setCancelable(false)
        onMain(savedInstanceState)

    }

    abstract fun getLayout(): Int
    abstract fun onMain(savedInstanceState: Bundle?)

    fun setLog(message: String) {
        Log.e("Log", message)
    }

    fun showDialog(Mdialog: String) {
        if (!pDialog.isShowing)
            pDialog.setMessage(Mdialog)
        pDialog.show()
    }

    fun hideDialog() {
        if (pDialog.isShowing)
            pDialog.dismiss()
    }

    fun getTAG(): String {
        return "TES APLIKASI"
    }

    public override fun onDestroy() {
        super.onDestroy()
    }


    fun setToast(str: String) {
        Toast.makeText(this@BaseActivity, str, Toast.LENGTH_LONG).show()
    }

    fun gotoActivity(clas: Class<*>) {
        startActivity(Intent(this, clas))
    }

    fun gotoActivityWithBundle(bundle: Bundle, clas: Class<*>) {
        val intent = Intent(this, clas)
        intent.putExtra("data", bundle)
        startActivity(intent)
    }
}