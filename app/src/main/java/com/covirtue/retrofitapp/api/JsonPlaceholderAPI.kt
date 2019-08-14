package com.covirtue.retrofitapp.api

import com.covirtue.retrofitapp.models.PostComments
import com.covirtue.retrofitapp.models.PostModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceholderAPI {

    @GET("posts")
    fun getPosts() : Call<List<PostModel>>

    @GET("posts/{id}/comments")
    fun getPostComments(@Path("id") commentId : Int) : Call<List<PostComments>>

}