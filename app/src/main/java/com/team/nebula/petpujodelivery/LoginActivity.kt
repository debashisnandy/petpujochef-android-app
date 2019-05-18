package com.team.nebula.petpujodelivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.team.nebula.petpujodelivery.acceptorders.OrderManageActivity
import kotlinx.android.synthetic.main.activity_login.*
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        buLogin.setOnClickListener { it ->

            auth.signInWithEmailAndPassword(enEmail.text.toString(),enPasswd.text.toString())
                .addOnCompleteListener {task ->
                    toast(enEmail.text.toString()+" "+enPasswd.text.toString())
                    if (task.isSuccessful) {

                        var int = Intent(this,OrderManageActivity::class.java)
                        startActivity(int)
                        finish()
                    } else {
                        longToast(R.string.wrng_cre)

                    }

                }

        }

    }
}
