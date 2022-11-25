package com.bagas.laporankecelakaan.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OtpResponse {

    @SerializedName("message")
    @Expose
    var message : String?=""

}