package com.flyco.tablayout.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import java.util.*

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    val data: MutableList<Fragment> = ArrayList()
    val titles: MutableList<String> = ArrayList()

    constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle, data: MutableList<out Fragment> = ArrayList(),titles: MutableList<out String> = ArrayList()) : this(fragmentManager, lifecycle) {
        this.data.addAll(data)
        this.titles.addAll(titles)
    }

    override fun getItemCount(): Int = data.size

    override fun createFragment(position: Int): Fragment = data[position]

    fun setCachePool(viewPager2: ViewPager2, offscreenPageLimit: Int, maxCacheCount: Int){
        viewPager2.offscreenPageLimit = offscreenPageLimit
        (viewPager2.getChildAt(0) as RecyclerView).setRecycledViewPool(RecyclerView.RecycledViewPool().apply { setMaxRecycledViews(0, maxCacheCount) })
        (viewPager2.getChildAt(0) as RecyclerView).setItemViewCacheSize(maxCacheCount)
    }

    fun setNewData(data: MutableList<out Fragment>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun addData(data: MutableList<out Fragment>){
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun clearData(){
        this.data.clear()
        notifyDataSetChanged()
    }

    fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}