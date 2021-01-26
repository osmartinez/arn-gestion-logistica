package com.arneplant.logisticainterna_kot2.adapter.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabPagerAdapter(fm:FragmentManager, behaviour:Int):FragmentPagerAdapter(fm,behaviour) {
    val fragments = ArrayList<Fragment>()
    val fragmentTitle = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitle[position];
    }

    fun addFragment(fragment: Fragment, title: String){
        fragments.add(fragment)
        fragmentTitle.add(title);
    }
}