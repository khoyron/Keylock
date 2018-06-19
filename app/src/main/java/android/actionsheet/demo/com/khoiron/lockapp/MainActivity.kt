package android.actionsheet.demo.com.khoiron.lockapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.app.Activity
import android.actionsheet.demo.com.khoiron.locklib.PinShow
import android.util.Log


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTest.setOnClickListener {

//            for fingger
            /*var intent = Intent(this,FingerPrintActivity::class.java)
            intent.putExtra(FingerPrintActivity.contant.FIRST,FingerPrintActivity.contant.NOTCANCELLED)
            startActivityForResult(intent,FingerPrintActivity.contant.FINGER)*/

//            for pin
/*            var int = Intent(this, PinShow::class.java)
            int.putExtra(PinShow.pinValue.FIRST,PinShow.pinValue.NOTCANCELLED)
//            int.putExtra(PinShow.pinValue.PIN,"1234")
            startActivityForResult(int,PinShow.pinValue.PINSHOW)*/
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==11){
            if (resultCode == Activity.RESULT_OK) {
                val result = data?.getStringExtra("result")
                setToast(result!!)
            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }

        }else if(requestCode==20){
            if (resultCode == Activity.RESULT_OK) {
                setToast("Success")
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                setToast("Cancelled")
            }
        }
    }

    private fun setLog(string: String) {
        Log.e("--","-->"+string)
    }

    private fun setToast(string: String) {
        Toast.makeText(applicationContext,string,Toast.LENGTH_LONG).show()
    }
}

