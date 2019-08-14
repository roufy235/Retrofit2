package com.covirtue.retrofitapp.models

import com.google.gson.annotations.SerializedName

data class PostModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("userId")
    val userId: Int
)