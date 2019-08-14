package com.covirtue.retrofitapp.api

import com.covirtue.retrofitapp.models.PostComments
import com.covirtue.retrofitapp.models.PostModel
import retrofit2.Call
import retrofit2.http.*

interface JsonPlaceholderAPI {

    @GET("posts")
    fun getPosts() : Call<List<PostModel>>


    //route ==> posts/1/comments
    @GET("posts/{id}/comments")
    fun getPostComments(@Path("id") commentId : Int) : Call<List<PostComments>>

    //route ==> posts?userId=1
    @GET("posts")
    fun getUserPost(@Query("userId") userId : Int) : Call<List<PostModel>>

    //to post to server
    @POST("posts")
    fun createPost(@Body post : PostModel) : Call<PostModel>


}