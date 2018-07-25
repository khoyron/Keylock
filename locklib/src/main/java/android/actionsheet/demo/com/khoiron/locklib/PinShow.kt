package android.actionsheet.demo.com.khoiron.locklib

import android.actionsheet.demo.com.khoiron.locklib.PinShow.pinValue.FIRST
import android.actionsheet.demo.com.khoiron.locklib.PinShow.pinValue.NOTCANCELLED
import android.actionsheet.demo.com.khoiron.locklib.PinShow.pinValue.NOTFIRST
import android.actionsheet.demo.com.khoiron.locklib.PinShow.pinValue.PIN
import android.actionsheet.demo.com.khoiron.locklib.PinShow.pinValue.data
import android.actionsheet.demo.com.khoiron.locklib.PinShow.pinValue.firs
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import kotlinx.android.synthetic.main.activity_pin_show.*
import android.app.Activity
import android.widget.Toast


class PinShow : AppCompatActivity() {

    val value by lazy { ArrayList<String>() }
    var notcancell = false
    var insert = false
    var pin = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_show)

        //handleINtent
        try {
            if (intent.getStringExtra(FIRST)!=null){
                if (FIRST.equals(intent.getStringExtra(FIRST))) {
                    insert = true
                }else if(NOTCANCELLED.equals(intent.getStringExtra(FIRST))){
                    notcancell = true
                    insert = true
                }
            }else if (intent.getStringExtra(NOTFIRST)!= null){
                if (NOTCANCELLED.equals(intent.getStringExtra(NOTFIRST))) {
                    notcancell = true
                }
                if (intent.getStringExtra(PIN)!=null){
                    pin = intent.getStringExtra(PIN)
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        drawImage()
        btnClik()
    }

    private fun btnClik() {
        b1.setOnClickListener {
            if (value.size > 4){

            }else{
                value.add("1")
                drawImage()
            }
        }
        b2.setOnClickListener {
            if (value.size > 4){

            }else{
                value.add("2")
                drawImage()
            }
        }
        b3.setOnClickListener {
            if (value.size > 4){

            }else{
                value.add("3")
                drawImage()
            }
        }
        b4.setOnClickListener {
            if (value.size > 4){

            }else{
                value.add("4")
                drawImage()
            }
        }
        b5.setOnClickListener {
            if (value.size > 4){

            }else{
                value.add("5")
                drawImage()
            }
        }
        b6.setOnClickListener {
            if (value.size> 4){

            }else{
                value.add("6")
                drawImage()
            }
        }
        b7.setOnClickListener {
            if (value.size > 4){

            }else{
                value.add("7")
                drawImage()
            }
        }
        b8.setOnClickListener {
            if (value.size > 4){

            }else{
                value.add("8")
                drawImage()
            }
        }
        b9.setOnClickListener {
            if (value.size > 4){

            }else{
                value.add("9")
                drawImage()
            }
        }
        b0.setOnClickListener {
            if (value.size > 4){

            }else{
                value.add("0")
                drawImage()
            }
        }
        bx.setOnClickListener {
            if (value.size > 4){

            }else{
                if(value.isNotEmpty()){
                    value.removeAt(value.size-1)
                    drawImage()
                }
            }
        }
    }

    private fun setLog(message: String) {
        Log.e("-> ", message)
    }

    private fun setToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    private fun drawImage() {
        setLog("--> "+value.toString())
        when (value.size) {
            0 -> {
                image1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
                image2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
                image3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
                image4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
            }
            1 -> {
                image1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle2))
                image2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
                image3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
                image4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
            }
            2 -> {
                setLog("2")
                image1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle2))
                image2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle2))
                image3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
                image4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
            }
            3 -> {
                setLog("3")
                image1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle2))
                image2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle2))
                image3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle2))
                image4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
            }
            4 -> {
                setLog("4")
                image1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle2))
                image2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle2))
                image3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle2))
                image4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle2))

            }

        }

        if (value.size == 4) {
            if(insert){
                if(firs){
                    for (i in 0..(value.size-1)){
                        data = data+value.get(i)
                    }

//                startActivity(Intent(applicationContext,PinShow::class.java))
                    Log.e("-> ", "Test ==== " + data)
                    firs = false
                    tittle.setText("Please retry 4 digit Pin code")
                    value.clear()
                    image1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
                    image2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
                    image3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
                    image4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
                }else{
                    var dataa = ""
                    for (i in 0..(value.size-1)){
                        dataa = dataa+value.get(i)
                    }
                    if (data.equals(dataa)){
//                    dataCallback = dataa
                        val returnIntent = Intent()
                        returnIntent.putExtra("result", dataa)
                        setResult(Activity.RESULT_OK, returnIntent)
                        finish()
                    }else{

                        setToast("Code not same ")
                        value.clear()
                        image1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
                        image2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
                        image3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
                        image4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))

                    }
                }
            }else{
                var dataa = ""
                for (i in 0..(value.size-1)){
                    dataa = dataa+value.get(i)
                }
                if (pin.equals(dataa)){
//                    dataCallback = dataa
                    val returnIntent = Intent()
                    returnIntent.putExtra("result", dataa)
                    setResult(Activity.RESULT_OK, returnIntent)
                    finish()
                }else{
                    setToast("Code not same ")
                    value.clear()
                    image1.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
                    image2.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
                    image3.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))
                    image4.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.cycle1))

                }
            }

        }

    }

    override fun onBackPressed() {

        if(!notcancell){
            firs = true
            data = ""
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        firs = true
        data = ""
    }

    object pinValue{
        var firs = true
        var data = ""

        var FIRST = "first"
        var NOTFIRST = "notfirst"
        var NOTCANCELLED = "notcancell"
        var PINSHOW = 11
        var PIN = "PIN"
    }


}