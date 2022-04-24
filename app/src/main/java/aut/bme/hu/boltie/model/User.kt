package aut.bme.hu.boltie.model

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("id") val id: Long,
    @SerializedName("email") val email: String,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("nickName") val nickName: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("accessKey") val accessKey: String
) { }
