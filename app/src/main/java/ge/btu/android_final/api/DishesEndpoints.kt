package ge.btu.android_final.api

import ge.btu.android_final.model.Dishes
import retrofit2.Call
import retrofit2.http.GET

interface DishesEndpoints {
    @GET("/api/v1/dishes")
    fun getDishes(): Call<Dishes>
}