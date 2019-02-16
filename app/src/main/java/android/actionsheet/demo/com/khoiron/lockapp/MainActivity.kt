package android.actionsheet.demo.com.khoiron.lockapp

import android.actionsheet.demo.com.khoiron.locklib.pin.pinlib.PinlibShow
import android.actionsheet.demo.com.khoiron.locklib.pin.pinlib.PinlibShowResult
import android.actionsheet.demo.com.khoiron.locklib.slide.SlideShow
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTest.setOnClickListener {

           /* SlideShow(this)
                    .slideShow()*/

            /*PinlibShow(this)
                    .setPin("1234")
                    .setUrl("https://i.ibb.co/brbxhbv/photo-2018-12-10-09-56-49.jpg")
                    .setType(false)
                    .setNotCancelled(true)
                    .showPinlib()*/

        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        PinlibShowResult(requestCode, resultCode, data, object : PinlibShowResult.callback {
            override fun data(data: Intent) {
                setToast(data?.getStringExtra("result"))
            }
        })

    }

    private fun setToast(string: String) {
        Toast.makeText(applicationContext, string, Toast.LENGTH_LONG).show()
    }
}

