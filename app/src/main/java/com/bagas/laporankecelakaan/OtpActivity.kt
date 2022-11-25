package com.bagas.laporankecelakaan

import android.annotation.SuppressLint
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.bagas.laporankecelakaan.api.Retro
import com.bagas.laporankecelakaan.api.UserApi
import com.bagas.laporankecelakaan.model.OtpRequest
import com.bagas.laporankecelakaan.model.OtpResponse
import com.bagas.laporankecelakaan.utils.Preferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OtpActivity : AppCompatActivity() {

    lateinit var preferences: Preferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        preferences = Preferences(this)
        val token = preferences.getValues("access_token").toString()
        Log.d("token otp", token.toString())

        findViewById<Button>(R.id.btn_otp).setOnClickListener {
            val request = OtpRequest()
            val otpData = "${findViewById<EditText>(R.id.otp1).text.toString().trim()}"+
                    "${findViewById<EditText>(R.id.otp2).text.toString().trim()}"+
                    "${findViewById<EditText>(R.id.otp3).text.toString().trim()}"+
                    "${findViewById<EditText>(R.id.otp4).text.toString().trim()}"+
                    "${findViewById<EditText>(R.id.otp5).text.toString().trim()}"+
                    "${findViewById<EditText>(R.id.otp6).text.toString().trim()}"
            request.verification_code = otpData
            val progresDialog = ProgressDialog(this)
            progresDialog.setTitle("Mohon Menunggu...")
            progresDialog.show()
            if (6 != otpData.length) {
                progresDialog.dismiss()
                Toast.makeText(this@OtpActivity, "Masukan Kode OTP terlebih dahulu", Toast.LENGTH_LONG).show()
            }
            val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
            retro.otp(accessToken = token, request).enqueue(object : Callback<OtpResponse>{
                override fun onResponse(call: Call<OtpResponse>, response: Response<OtpResponse>) {
                    progresDialog.dismiss()
                    val data = response.body()
                    Log.d("response", "APA"+data?.message)
                }
                override fun onFailure(call: Call<OtpResponse>, t: Throwable) {
                    progresDialog.dismiss()
                    Log.e("error", "error apa ini"+t.message)
                    Toast.makeText(this@OtpActivity, "Faild"+t.message, Toast.LENGTH_LONG).show()
                }
            })
            Log.d("verrif", request.verification_code.toString())
        }
    }
}