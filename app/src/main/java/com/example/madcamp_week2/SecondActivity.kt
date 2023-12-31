package com.example.madcamp_week2

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.madcamp_week2.databinding.ActivitySecondBinding
import com.kakao.sdk.user.UserApiClient
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private lateinit var ID: String

    class MyFragmentPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

        val fragments = listOf(OneFragment(), TwoFragment(), ThreeFragment(), FourFragment(), FiveFragment())

        override fun getItemCount(): Int = fragments.size
        override fun createFragment(position: Int): Fragment = fragments[position]
    }

    fun tabSetting(tab: TabLayout.Tab, position: Int) {
        if (position == 0) {
            tab.text = "Post"
            tab.setIcon(R.drawable.dashboard_icon)
        } else if (position == 1) {
            tab.text = "Crew"
            tab.setIcon(R.drawable.frag5_crew_icon)
        } else if (position ==2){
            tab.text = "Add"
            tab.setIcon(R.drawable.add_icon)
        }else if (position ==3) {
            tab.text = "Map"
            tab.setIcon(R.drawable.map_icon)
        }else{
            tab.text = "My"
            tab.setIcon(R.drawable.profile_icon)
        }

    }

    private fun sendDataToFragment(fragment: Fragment) {
        if (fragment is OneFragment) {
            fragment.setResponseData(ID)
        } else if (fragment is TwoFragment) {
            fragment.setResponseData(ID)
        } else if (fragment is ThreeFragment) {
            fragment.setResponseData(ID)
        } else if (fragment is FourFragment) {
            fragment.setResponseData(ID)
        } else if (fragment is FiveFragment) {
            fragment.setResponseData(ID)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ID = intent.getStringExtra("ID")?:""


        binding.viewpager.adapter = MyFragmentPagerAdapter(this)
        binding.viewpager.isUserInputEnabled = false
        TabLayoutMediator(binding.tabs,binding.viewpager){
                tab, position-> tabSetting(tab, position)
        }.attach()

        val adapter = binding.viewpager.adapter as MyFragmentPagerAdapter
        for (fragment in adapter.fragments) {
            sendDataToFragment(fragment)
        }
    }
}