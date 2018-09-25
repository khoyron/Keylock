package android.actionsheet.demo.com.khoiron.locklib.slide

import android.actionsheet.demo.com.khoiron.locklib.R
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Fragment1 : Fragment() {

    lateinit var btnNext: Button
    lateinit var nameMerchant: TextView
    lateinit var btn_back: ImageView
    lateinit var btn_back_home: ImageView
    lateinit var connextDataListener: connextData

    fun setConnext(connextData: connextData){
        connextDataListener = connextData
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater?.inflate(R.layout.fragment_tutorial1, container, false)
        Log.e("test aplikasi ==> ", "fragment_tutorial")

        btnNext = layout.findViewById(R.id.btn_next)as Button
        nameMerchant = layout.findViewById(R.id.nameMerchant) as TextView/*
        btn_back = layout.findViewById(R.id.btn_back)as ImageView
        btn_back_home = layout.findViewById(R.id.btn_back_home)as ImageView*/


        return layout
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener {
            connextDataListener.onclikButton()
        }

       /* val bundle = arguments

        if (bundle != null) {
            try {
               tutorialImage.setImageBitmap(dataq.get(bundle.getInt("position")))
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }*/

    }

    object intance{
        lateinit var dataq :MutableList<Bitmap>

        fun getInstance(position: Int, data :MutableList<Bitmap>):Fragment{
            val myFragment = Fragment1()
            val args = Bundle()
            dataq = data

            args.putInt("position", position)
            myFragment.arguments = args

            return myFragment
        }

    }

    interface connextData{
        fun onclikButton()
    }


}