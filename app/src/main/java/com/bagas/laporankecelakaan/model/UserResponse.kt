package com.bagas.laporankecelakaan.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse {

    @SerializedName("user")
    @Expose
    var user : User ? = null
    class User {
        @SerializedName("email")
        @Expose
        var email : String ? = null

        @SerializedName("name")
        @Expose
        var name : String ? = null

        @SerializedName("phone")
        @Expose
        var phone : String ? = null

        @SerializedName("transportation_type")
        @Expose
        var transportation_type : String ? = null

        @SerializedName("transportation_num")
        @Expose
        var transportation_num : String ? = null
    }

    @SerializedName("token")
    @Expose
    var token : Token ? = null
    class Token {
        @SerializedName("access_token")
        @Expose
        var access_token : String ? = null

        @SerializedName("token_type")
        @Expose
        var token_type : String ? = null

        @SerializedName("expires_in")
        @Expose
        var expires_in : String ? = null
    }
}