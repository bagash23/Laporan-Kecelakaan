package com.bagas.laporankecelakaan

import android.Manifest
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bagas.laporankecelakaan.api.Retro
import com.bagas.laporankecelakaan.api.UserApi
import com.bagas.laporankecelakaan.model.BeritaAdapter
import com.bagas.laporankecelakaan.model.BeritaResponse
import com.bagas.laporankecelakaan.utils.Preferences
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity() {

    lateinit var preferences: Preferences

    private lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    private lateinit var gecoder : Geocoder

    private var list = ArrayList<BeritaResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        gecoder = Geocoder(this, Locale.getDefault())
        getLastLocation()


        preferences = Preferences(this)
        findViewById<TextView>(R.id.tv_name_home).setText(preferences.getValues("name"))
        findViewById<TextView>(R.id.tv_email_home).setText(preferences.getValues("email"))
        findViewById<TextView>(R.id.tv_type_kendaraan).setText(preferences.getValues("transportation_type"))
        findViewById<TextView>(R.id.tv_number_kendaraan).setText(preferences.getValues("transportation_num"))

        findViewById<RecyclerView>(R.id.rv_berita).layoutManager = LinearLayoutManager(this)
        getData()

    }


    private fun getData() {
        val retro = Retro().getRetroClientInstance().create(UserApi::class.java)
        retro.getBerita().enqueue(object : Callback<ArrayList<BeritaResponse>> {
            override fun onResponse(
                call: Call<ArrayList<BeritaResponse>>,
                response: Response<ArrayList<BeritaResponse>>
            ) {
                list.addAll(response.body()!!)
                findViewById<RecyclerView>(R.id.rv_berita).adapter = BeritaAdapter(list)
            }

            override fun onFailure(call: Call<ArrayList<BeritaResponse>>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "Faild"+t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 10101)
        }

        val lastLoaction = fusedLocationProviderClient.lastLocation
        lastLoaction.addOnSuccessListener {
            Log.d("lat","getLocation: ${it.latitude}"  )
            Log.d("long","getLocation: ${it.longitude}")
            val address = gecoder.getFromLocation(it.latitude, it.longitude, 1)
            findViewById<TextView>(R.id.tv_lokasi_now).text = address[0].getAddressLine(0)
        }

        lastLoaction.addOnFailureListener {
            Log.d("FAIL", "getLocation FAILED")
        }

    }
}