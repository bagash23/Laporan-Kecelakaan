package com.bagas.laporankecelakaan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.os.postDelayed
import com.bagas.laporankecelakaan.utils.Preferences

class SplashActivity : AppCompatActivity() {

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        preferences = Preferences(this)

        var handle = Handler()
//        if (preferences.getValues("userLogin").equals("1")) {
//            handle.postDelayed({
//                finishAffinity()
//                var intent = Intent(this@SplashActivity, HomeActivity::class.java)
//                startActivity(intent)
//            }, 3000)
//        } else {
//
//        }

        handle.postDelayed({
            finishAffinity()
            var intent = Intent(this@SplashActivity, MasukActivity::class.java)
            startActivity(intent)
        }, 3000)


    }
}