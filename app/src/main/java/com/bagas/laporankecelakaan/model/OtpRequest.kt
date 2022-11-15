package com.bagas.laporankecelakaan.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OtpRequest {

    @SerializedName("verification_code")
    @Expose
    var verification_code : String ? = null

}