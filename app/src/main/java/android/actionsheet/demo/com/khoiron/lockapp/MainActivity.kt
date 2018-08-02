package android.actionsheet.demo.com.khoiron.lockapp

import android.actionsheet.demo.com.khoiron.locklib.FingerPrintActivity
import android.actionsheet.demo.com.khoiron.locklib.Pinlib
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.app.Activity
import android.util.Log


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTest.setOnClickListener {

//            startActivity(Intent(this,Pinlib::class.java))
//            for fingger
//            var intent = Intent(this,FingerPrintActivity::class.java)
//            intent.putExtra(FingerPrintActivity.contant.FIRST,FingerPrintActivity.contant.NOTCANCELLED)
//            startActivityForResult(intent,FingerPrintActivity.contant.FINGER)
//            for pin

            var int = Intent(this, Pinlib::class.java)
            int.putExtra(Pinlib.pinValue.NOTFIRST,Pinlib.pinValue.NOTCANCELLED)
            int.putExtra(Pinlib.pinValue.PIN,"1234")
            int.putExtra(Pinlib.pinValue.URL_IMAGE,"http://206.189.88.9/assets/img/media/background.png")
            startActivityForResult(int,Pinlib.pinValue.PINSHOW)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==11){
            if (resultCode == Activity.RESULT_OK) {
                if(Pinlib.pinValue.FORGOT.equals(data?.getStringExtra("result"))){

                }else{

                }
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

