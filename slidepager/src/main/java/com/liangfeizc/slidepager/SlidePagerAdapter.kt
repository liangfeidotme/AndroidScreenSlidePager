package com.liangfeizc.slidepager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*

/**
 * Created by liangfei on 3/26/15.
 */
class SlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private var picList = mutableListOf<String>()

    override fun getItem(i: Int): Fragment {
        return SlidePageFragment.newInstance(picList[i])
    }

    override fun getCount() = picList.size

    fun addAll(picList: List<String>) {
        this.picList.apply {
            clear()
            addAll(picList)
        }
    }
}
