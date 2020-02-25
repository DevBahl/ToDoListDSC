package com.dbsrm.todolistdsc

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class walkthrfragpageradapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when(position){

            0 -> firstwalkthrfragment.newInstance()
            1 -> secondwalkthrfragment.newInstance()
            else -> firstwalkthrfragment.newInstance()
        }
    }

    override fun getCount() = 2
}