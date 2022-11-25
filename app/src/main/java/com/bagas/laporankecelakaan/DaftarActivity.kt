package com.bagas.laporankecelakaan

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.bagas.laporankecelakaan.api.Retro
import com.bagas.laporankecelakaan.api.UserApi
import com.bagas.laporankecelakaan.model.UserDaftarRequest
import com.bagas.laporankecelakaan.model.UserDaftarResponse
import com.bagas.laporankecelakaan.utils.Preferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class DaftarActivity : AppCompatActivity() {

    var statusCar : Boolean = false
    var statusTruck : Boolean = false
    var statusMotor : Boolean = false
    var selectKendaraan : String = ""

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)

        preferences = Preferences(this)

        findViewById<ImageView>(R.id.car).setOnClickListener {
            if (statusCar) {
                findViewById<ImageView>(R.id.car).setImageResource(R.drawable.ic_baseline_directions_car_24)
                statusCar = false
                selectKendaraan = ""
                pick(selectKendaraan)
            } else {
                findViewById<ImageView>(R.id.car).setImageResource(R.drawable.ic_baseline_directions_car_active)
                statusCar = true
                selectKendaraan = "CARS"
                pick(selectKendaraan)
            }
        }

        findViewById<ImageView>(R.id.truck).setOnClickListener {
            if (statusTruck) {
                findViewById<ImageView>(R.id.car).setImageResource(R.drawable.ic_baseline_directions_bus_24)
                statusTruck = false
                selectKendaraan = ""
                pick(selectKendaraan)
            } else {
                findViewById<ImageView>(R.id.car).setImageResource(R.drawable.ic_baseline_directions_bus_active)
                statusTruck = true
                selectKendaraan = "TRUCK"
                pick(selectKendaraan)
            }
        }

        findViewById<ImageView>(R.id.motor).setOnClickListener {
            if (statusMotor) {
                findViewById<ImageView>(R.id.car).setImageResource(R.drawable.ic_baseline_moped_24)
                statusMotor = false
                selectKendaraan = ""
                pick(selectKendaraan)
            } else {
                findViewById<ImageView>(R.id.car).setImageResource(R.drawable.ic_baseline_moped_active)
                statusMotor = true
                selectKendaraan = "MOTORCYCLE"
                pick(selectKendaraan)
            }
        }
    }

    private fun pick(select : String) {
        if (select == "") {
            Log.d("kosong pick", "Tidak ada yang dipick")
        } else {
            findViewById<Button>(R.id.btn_daftar).setOnClickListener {
                val request = UserDaftarRequest()
                request.name = findViewById<EditText>(R.id.et_nama_daftar).text.toString().trim()
                request.phone = findViewById<EditText>(R.id.et_no_hp_daftar).text.toString().trim()
                request.email = findViewById<EditText>(R.id.et_email_daftar).text.toString().trim()
                request.transportation_type = select
                request.transportation_num = findViewById<EditText>(R.id.et_no_kendaraan_daftar).text.toString().trim()
                request.password = findViewById<EditText>(R.id.et_password_daftar).text.toString().trim()

                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Mohon Menunggu...")
                progressDialog.show()

                val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
                retro.register(request).enqueue(object : Callback<UserDaftarResponse> {
                    override fun onResponse(
                        call: Call<UserDaftarResponse>,
                        response: Response<UserDaftarResponse>
                    ) {

                        val user = response.body()
                        progressDialog.dismiss()
                        finishAffinity()
                        preferences.setvalues("access_token", user?.token?.access_token.toString())
                       val intent = Intent(this@DaftarActivity, OtpActivity::class.java)
                        startActivity(intent)
                    }

                    override fun onFailure(call: Call<UserDaftarResponse>, t: Throwable) {
                        progressDialog.dismiss()
                        Toast.makeText(this@DaftarActivity, "Faild"+t.message, Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }
}