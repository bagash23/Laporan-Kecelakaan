package com.bagas.laporankecelakaan.api

import com.bagas.laporankecelakaan.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST

interface UserApi {
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
    @Multipart
    fun otp (
        @Header("Authorization") accessToken:String,
        @Body otpRequest: OtpRequest
    ) : Call<OtpResponse>
}