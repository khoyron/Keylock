package android.actionsheet.demo.com.khoiron.locklib.pin

import android.actionsheet.demo.com.khoiron.locklib.R
import android.actionsheet.demo.com.khoiron.locklib.pin.Pinlib.pinValue.FIRST
import android.actionsheet.demo.com.khoiron.locklib.pin.Pinlib.pinValue.FORGOT
import android.actionsheet.demo.com.khoiron.locklib.pin.Pinlib.pinValue.NOTCANCELLED
import android.actionsheet.demo.com.khoiron.locklib.pin.Pinlib.pinValue.NOTFIRST
import android.actionsheet.demo.com.khoiron.locklib.pin.Pinlib.pinValue.PIN
import android.actionsheet.demo.com.khoiron.locklib.pin.Pinlib.pinValue.URL_IMAGE
import android.actionsheet.demo.com.khoiron.locklib.pin.Pinlib.pinValue.URL_IMG
import android.actionsheet.demo.com.khoiron.locklib.pin.Pinlib.pinValue.data
import android.actionsheet.demo.com.khoiron.locklib.pin.Pinlib.pinValue.firs
import android.actionsheet.demo.com.khoiron.locklib.base.BaseActivity
import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.util.TypedValue
import android.view.View
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pinlib.*

/**
 * Created by khoiron on 23/07/18.
 */

class Pinlib : BaseActivity() {

    var notcancell = false
    var insert = false
    var pin = ""

    val recyclerView by lazy { findViewById(R.id.recycler)as RecyclerView }
    val adapterRecycler by lazy { AdapterRecycler(this) }
    var mutableList :MutableList<modelNumber> = ArrayList<modelNumber>()
    var value :MutableList<String> = ArrayList<String>()

    override fun getLayout(): Int {
        return R.layout.pinlib
    }

    override fun onMain(savedInstanceState: Bundle?) {

        typePin()
        setInitRecycler()
        onClikRecycler()

        getBackground()
        forgotPassword.setOnClickListener { forgotListener() }
    }

    private fun typePin() {
        try {
            if (intent.getStringExtra(FIRST)!=null){

                if (FIRST.equals(intent.getStringExtra(FIRST))) {
                    insert = true
                    tittle.setText("Set 4 digit Pin code")
                }else if(NOTCANCELLED.equals(intent.getStringExtra(FIRST))){
                    notcancell = true
                    insert = true
                }
                Log.e("LOG",intent.getStringExtra(URL_IMAGE))
                URL_IMG = ""
                URL_IMG = intent.getStringExtra(URL_IMAGE)

            }else if (intent.getStringExtra(NOTFIRST)!= null){
                if (NOTCANCELLED.equals(intent.getStringExtra(NOTFIRST))) {
                    notcancell = true
                }
                if (intent.getStringExtra(PIN)!=null){
                    pin = intent.getStringExtra(PIN)

                }
                Log.e("LOG",intent.getStringExtra(URL_IMAGE))
                URL_IMG = ""
                URL_IMG = intent.getStringExtra(URL_IMAGE)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun onClikRecycler() {
        adapterRecycler.onclik(object : AdapterRecycler.onclickListener {
            override fun onclik(view: Int, position: Int) {
                if(view==-1){
                    if (position==11){
                        if (value.isNotEmpty()){
                            value.removeAt(value.size-1)
                            var data = ""
                            for(i in (0..value.size-1)){
                                data = data+value.get(i)
                            }
                            txvalue.text = data
                        }
                    }else{
                        value.add(mutableList.get(position).number)
                        var data = ""
                        for(i in (0..value.size-1)){
                            data = data+value.get(i)
                        }
                        txvalue.text = data
                    }

                }

                if (value.size == 4) {
                    if(insert){
                        if(firs){
                            for (i in 0..(value.size-1)){
                                data = data+value.get(i)
                            }
                            firs = false
                            incorrect("Please retry 4 digit Pin code")

                        }else{
                            var dataa = ""
                            for (i in 0..(value.size-1)){
                                dataa = dataa+value.get(i)
                            }
                            if (data.equals(dataa)){
                                val returnIntent = Intent()
                                returnIntent.putExtra("result", dataa)
                                setResult(Activity.RESULT_OK, returnIntent)
                                finish()
                            }else{
                                incorrect("Code not same ")

                            }
                        }
                    }else{
                        var dataa = ""
                        for (i in 0..(value.size-1)){
                            dataa = dataa+value.get(i)
                        }
                        if (pin.equals(dataa)){
                            val returnIntent = Intent()
                            returnIntent.putExtra("result", dataa)
                            setResult(Activity.RESULT_OK, returnIntent)
                            finish()
                        }else{
                            incorrect("Code not same ")

                        }

                        /*setLog("benar")
                        var dataa = ""
                        for (i in 0..(value.size-1)){
                            dataa = dataa+value.get(i)
                        }
                        val returnIntent = Intent()
                        returnIntent.putExtra("result", dataa)
                        setResult(Activity.RESULT_OK, returnIntent)
                        finish()*/
                    }

                }
            }
        })

    }

    private fun forgotListener() {
        val returnIntent = Intent()
        returnIntent.putExtra("result", FORGOT)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    fun incorrect(message :String){
        Handler().postDelayed(Runnable {
            txvalue.text = ""
        }, 400)
        setToast(message)
        value.clear()
    }

    fun incorrectPin(message :String){
        Handler().postDelayed(Runnable {
            txvalue.text = ""
        }, 400)
        txError.setText(message)
        value.clear()
    }

    private fun getBackground() {
        Picasso.get()
                .load(URL_IMG)
                .fit()
                .centerCrop()
                .into(image,object :Callback{
                    override fun onSuccess() {

                    }

                    override fun onError(e: java.lang.Exception?) {
                        getBackground()
                    }
                })
    }

    private fun setInitRecycler() {

        for (i in 1..9){
            val modelNumber = modelNumber()
            modelNumber.number = "${i}"
            mutableList.add(modelNumber)
        }

        val modelNumber1 = modelNumber()
        modelNumber1.number = ""
        mutableList.add(modelNumber1)

        val modelNumber2 = modelNumber()
        modelNumber2.number = "0"
        mutableList.add(modelNumber2)

        val modelNumber3 = modelNumber()
        modelNumber3.number = "10"
        mutableList.add(modelNumber3)

        val mLayoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(GridSpacingItemDecoration(3, dpToPx(2), true))
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapterRecycler

        adapterRecycler.mutableList = mutableList
        adapterRecycler.notifyDataSetChanged()

    }

    inner class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
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
        var URL_IMAGE = ""
        var URL_IMG = ""
        var FORGOT = "forgot"
    }
}