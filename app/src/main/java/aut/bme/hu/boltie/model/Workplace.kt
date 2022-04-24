package aut.bme.hu.boltie.model

import com.google.gson.annotations.SerializedName

class Workplace(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
) {
}