package com.example.lovecalculator.network

import com.example.lovecalculator.data.ResultResponse
import com.example.lovecalculator.util.Constants.Companion.API_KEY
import com.example.lovecalculator.util.Constants.Companion.HEADER_HOST
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {

    @Headers("x-rapidapi-key: $API_KEY","x-rapidapi-host:$HEADER_HOST")
    @GET("getPercentage")
    suspend fun getPercentage(
        @Query("fname") firstName:String,
        @Query("sname") partnerName:String
    ):ResultResponse

}