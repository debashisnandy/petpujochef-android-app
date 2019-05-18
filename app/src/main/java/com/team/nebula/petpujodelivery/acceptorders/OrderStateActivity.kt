package com.team.nebula.petpujodelivery.acceptorders

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.team.nebula.petpujodelivery.R
import com.team.nebula.petpujodelivery.acceptorders.fragmentscollection.SimpleFragmentAdapter
import kotlinx.android.synthetic.main.activity_order_manage.*
import kotlinx.android.synthetic.main.activity_order_state.*

class OrderStateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_state)
        val viewPage: ViewPager = viewPage
        val simpleFragmentAdapter = SimpleFragmentAdapter(this,supportFragmentManager)
        viewPage.adapter = simpleFragmentAdapter
        val tabLayout: TabLayout = tabs
        tabLayout.setupWithViewPager(viewPage)
//        buActOrd.setOnClickListener { it ->
//            var int = Intent(this,OrderManageActivity::class.java)
//            startActivity(int)
//        }
    }
}
