package android.actionsheet.demo.com.khoiron.lockapp

import android.actionsheet.demo.com.khoiron.locklib.pin.PinOtp
import android.actionsheet.demo.com.khoiron.locklib.slide.ActivitySlider
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
//            var intent j= Intent(this,FingerPrintActivity::class.java)
//            intent.putExtra(FingerPrintActivity.contant.FIRST,FingerPrintActivity.contant.NOTCANCELLED)
//            startActivityForResult(intent,FingerPrintActivity.contant.FINGER)
//            for pin

//            startActivity(Intent(this,ActivitySlider::class.java))

            var int = Intent(this, PinOtp::class.java)
            int.putExtra(PinOtp.pinValue.NOTFIRST, PinOtp.pinValue.NOTCANCELLED)
            int.putExtra(PinOtp.pinValue.PIN,"1234")
            int.putExtra(PinOtp.pinValue.NO_PHONE,"0858455609")
            int.putExtra(PinOtp.pinValue.CODE,"5267")
            int.putExtra(PinOtp.pinValue.TITLE,"Kami telah mengirimkan kode ke \n" +
                    "081 1234 1234")
            int.putExtra(PinOtp.pinValue.URL_IMAGE,"http://13.251.205.142/assets/img/media/background.png")
            startActivityForResult(int, PinOtp.pinValue.PINSHOW)

        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==11){
            if (resultCode == Activity.RESULT_OK) {
               setLog(data?.getStringExtra("result")+" ")
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

