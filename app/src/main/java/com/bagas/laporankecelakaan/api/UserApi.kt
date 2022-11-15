package com.bagas.laporankecelakaan.api

import com.bagas.laporankecelakaan.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserApi {

    var token  :String

    @POST("api/login")
    fun login (
        @Body userRequest: UserRequest
    ) : Call<UserResponse>

    @GET("api/reports")
    fun getBerita() : Call<ArrayList<BeritaResponse>>

    @POST("api/register")
    fun register (
        @Body userDaftarRequest: UserDaftarRequest
    ) : Call<UserDaftarResponse>

    @POST("api/verify-phone")
    fun otp (
        @Body otpRequest: OtpRequest
    )
}