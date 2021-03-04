package com.example.lovecalculator.repository

import com.example.lovecalculator.network.Api
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResultRepository @Inject constructor(private val api: Api) {
    suspend fun getResults(firstName:String,partnerName:String) = api.getPercentage(firstName, partnerName)
}