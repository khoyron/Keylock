package android.actionsheet.demo.com.khoiron.locklib.slide

import android.actionsheet.demo.com.khoiron.locklib.R
import android.actionsheet.demo.com.khoiron.locklib.base.BaseActivity
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.slide_activity.*
import me.relex.circleindicator.CircleIndicator

/**
 * Created by khoiron on 24/09/18.
 */

class ActivitySlider :BaseActivity() {

    override fun getLayout(): Int {
        return R.layout.slide_activity
    }

    override fun onMain(savedInstanceState: Bundle?) {
        getViewPager()

    }

    private fun getViewPager() {

        var data : MutableList<Fragment> = ArrayList<Fragment>()
        var fiewPager = findViewById(R.id.fiewPager) as ViewPager
        var indicator = findViewById(R.id.indicator) as CircleIndicator

        var fragment1 = Fragment1()
        var fragment2 = Fragment2()
        var fragment3 = Fragment3()

        data.add(fragment1)
        data.add(fragment2)
        data.add(fragment3)

        var pagerAdapter = TutorialAdpt(supportFragmentManager, data)
        fiewPager.adapter = pagerAdapter
        indicator.setViewPager(fiewPager)

        fiewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

            }
        })

        fragment1.setConnext(object :Fragment1.connextData{
            override fun onclikButton() {
                finishListener()
            }
        })

        btn_back.setOnClickListener {
            when (fiewPager.currentItem){
                0 -> { fiewPager.setCurrentItem(2,true) }
                1 -> { fiewPager.setCurrentItem(0,true) }
                2 -> { fiewPager.setCurrentItem(1,true) }
            }
        }

        btn_back_home.setOnClickListener {
            finishListener()
        }

    }

    private fun finishListener() {
        val returnIntent = Intent()
        returnIntent.putExtra("result", "data")
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}