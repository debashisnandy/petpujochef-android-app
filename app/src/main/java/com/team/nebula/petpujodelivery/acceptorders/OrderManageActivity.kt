package com.team.nebula.petpujodelivery.acceptorders

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.team.nebula.petpujodelivery.R
import kotlinx.android.synthetic.main.activity_order_manage.*

class OrderManageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_manage)
        buActOrd.setOnClickListener { it ->
            var int = Intent(this,OrderStateActivity::class.java)
          startActivity(int)
        }
    }
}
