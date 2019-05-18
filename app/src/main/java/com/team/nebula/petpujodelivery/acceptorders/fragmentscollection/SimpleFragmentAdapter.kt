package com.team.nebula.petpujodelivery.acceptorders.fragmentscollection

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.team.nebula.petpujodelivery.R

class SimpleFragmentAdapter(context: Context ,fm: FragmentManager): FragmentPagerAdapter(fm) {

    private var mContext:Context? = null

    init {
        this.mContext=context
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return when (position) {
            0 -> mContext!!.getString(R.string.current_ord)
            1 -> mContext!!.getString(R.string.upcmng_order)
            else -> null
        }
    }

    override fun getItem(position: Int): Fragment {

        return when(position){
            0 -> CurrentOrderFragment()
            1 -> UpcomingOrderFragment()
            else -> null!!
        }
    }


    override fun getCount(): Int {
        return 2
    }
}