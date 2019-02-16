package android.actionsheet.demo.com.khoiron.locklib.slide

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


/**
 * Created by khoiron on 09/09/18.
 */
class TutorialAdpt(fm: FragmentManager, fragment: MutableList<Fragment>) : FragmentPagerAdapter(fm) {

    var frag = fragment

    override fun getCount(): Int {
        return frag.size
    }

    override fun getItem(position: Int): Fragment {
        return frag.get(position)
    }

}