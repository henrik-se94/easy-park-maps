package com.sigma.easyparkmap.retrofit

import com.sigma.easyparkmap.module.GetCities
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {
    @GET("https://pgroute-staging.easyparksystem.net/cities")
    fun getCitiesData(): Call<GetCities>

    companion object {
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(RetrofitSetup.BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}