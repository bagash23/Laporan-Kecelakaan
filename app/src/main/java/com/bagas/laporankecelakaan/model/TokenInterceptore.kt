package com.bagas.laporankecelakaan.model

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptore constructor(

    private  val access_token: String,
    private val token_type : String

) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", "$token_type $access_token")
            .build()
        return chain.proceed(request)
    }
}