package aut.bme.hu.boltie.model

import com.google.gson.annotations.SerializedName

class Product(
    @SerializedName("id") val id: Long,
    @SerializedName("barcode") val barcode: String,
    @SerializedName("name") val name: String,
    @SerializedName("desc") val desc: String,
    @SerializedName("price") val price: Float
) {
}