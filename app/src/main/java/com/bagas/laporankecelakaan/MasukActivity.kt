package com.bagas.laporankecelakaan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.bagas.laporankecelakaan.api.Retro
import com.bagas.laporankecelakaan.api.UserApi
import com.bagas.laporankecelakaan.model.UserRequest
import com.bagas.laporankecelakaan.model.UserResponse
import com.bagas.laporankecelakaan.utils.Preferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MasukActivity : AppCompatActivity() {

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masuk)

        preferences = Preferences(this)
//        preferences.setvalues("userLogin", "1")
//        if (preferences.getValues("status").equals("1")) {
//            finishAffinity()
//            val intent = Intent(this@MasukActivity, HomeActivity::class.java)
//            startActivity(intent)
//        }

        initAction()

        findViewById<TextView>(R.id.btn_create).setOnClickListener {
            val intent = Intent(this@MasukActivity, DaftarActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initAction() {
        findViewById<Button>(R.id.btn_masuk).setOnClickListener {
            login()
        }
    }

    private fun login() {
//        bikin request yang dipanggil dari user Request
        val request = UserRequest()
        request.email = findViewById<EditText>(R.id.et_email_masuk).text.toString().trim()
        request.password = findViewById<EditText>(R.id.et_password_masuk).text.toString().trim()
        //        memnaggil api
        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        //        mengcall back response
        retro.login(request).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val user = response.body()
                Log.d("users", user?.user.toString())
                Log.d("tokens", user?.token?.access_token.toString())

                if (user!!.user == null) {
                    Toast.makeText(this@MasukActivity, "data Anda tidak ditemukan", Toast.LENGTH_LONG).show()
                } else {
                    if (user?.user?.email.equals(request.email)) {
                        preferences.setvalues("email", user?.user?.email.toString())
                        preferences.setvalues("name", user?.user?.name.toString())
                        preferences.setvalues("phone", user?.user?.phone.toString())
                        preferences.setvalues("transportation_type", user?.user?.transportation_type.toString())
                        preferences.setvalues("transportation_num", user?.user?.transportation_num.toString())
                        preferences.setvalues("access_token", user?.token?.access_token.toString())
                        preferences.setvalues("token_type", user?.token?.token_type.toString())
                        preferences.setvalues("expires_in", user?.token?.expires_in.toString())
                        val intent = Intent(this@MasukActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@MasukActivity, "email tidak sama", Toast.LENGTH_LONG).show()
                    }
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@MasukActivity, "Faild"+t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}