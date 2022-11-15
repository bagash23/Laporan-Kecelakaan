package com.bagas.laporankecelakaan

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.bagas.laporankecelakaan.model.OtpRequest
import com.bagas.laporankecelakaan.utils.Preferences
import kotlin.math.log

class OtpActivity : AppCompatActivity() {

    lateinit var preferences: Preferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        preferences = Preferences(this)
        val token = preferences.getValues("access_token")
        Log.d("token otp", token.toString())

        findViewById<Button>(R.id.btn_otp).setOnClickListener {

            val request = OtpRequest()
            request.verification_code = findViewById<EditText>(R.id.otp1).text.toString().trim()
            request.verification_code = findViewById<EditText>(R.id.otp2).text.toString().trim()
            request.verification_code = findViewById<EditText>(R.id.otp3).text.toString().trim()
            request.verification_code = findViewById<EditText>(R.id.otp4).text.toString().trim()
            request.verification_code = findViewById<EditText>(R.id.otp5).text.toString().trim()
            request.verification_code = findViewById<EditText>(R.id.otp6).text.toString().trim()

            Log.d("verrif", request.verification_code.toString())


        }

    }
}