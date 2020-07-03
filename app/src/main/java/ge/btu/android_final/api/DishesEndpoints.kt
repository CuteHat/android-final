package ge.btu.android_final.api

import ge.btu.android_final.model.Dishes
import ge.btu.android_final.model.Location
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DishesEndpoints {
    @GET("/api/v1/dishes")
    fun getDishes(): Call<Dishes>

    @GET("/api/v1/dishes/{dishName}")
    fun getDishLocations(@Path("dishName") dishName: String): Call<Dishes>
}