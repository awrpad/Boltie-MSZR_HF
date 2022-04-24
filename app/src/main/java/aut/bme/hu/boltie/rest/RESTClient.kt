package aut.bme.hu.boltie.rest

import aut.bme.hu.boltie.model.Product
import aut.bme.hu.boltie.model.Role
import aut.bme.hu.boltie.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface RESTClient {
    @GET("user/{email}")
    fun getUserByEmail(@Path("email") email: String): Call<User>

    @DELETE("user/{userId}/manage")
    fun delUserById(@Path("userId") userId: Long): Call<ResponseBody>

    @GET("product/{barcode}/{workplaceId}")
    fun getProductByBarcodeAndWorkplace(
        @Path("barcode") barcode: String,
        @Path("workplaceId") wpId: String
    ): Call<Product>

    @GET("workplace/{wpId}")
    fun getWorkplaceById(@Path("wpId") wpId: String)

    @GET("/role/{userId}/{wpId}")
    fun getRoleByUserAndWorkplaceId(
        @Path("userId") userId: Long,
        @Path("wpId") wpId: String
    ): Call<Role>
}