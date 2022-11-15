package com.bagas.laporankecelakaan.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserDaftarRequest {

    @SerializedName("name")
    @Expose
    var name : String ? = null

    @SerializedName("phone")
    @Expose
    var phone : String ? = null

    @SerializedName("email")
    @Expose
    var email : String ? = null

    @SerializedName("transportation_type")
    @Expose
    var transportation_type : String ? = null

    @SerializedName("transportation_num")
    @Expose
    var transportation_num : String ? = null

    @SerializedName("password")
    @Expose
    var password : String ? = null

}