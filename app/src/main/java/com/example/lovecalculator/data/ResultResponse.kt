package com.example.lovecalculator.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultResponse(
    val fname:String,
    val sname:String,
    val percentage:Int,
    val result: String
):Parcelable
