package com.covirtue.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.covirtue.retrofitapp.api.JsonPlaceholderAPI
import com.covirtue.retrofitapp.models.PostComments
import com.covirtue.retrofitapp.models.PostModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var api : JsonPlaceholderAPI
    private lateinit var posts : List<PostModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(JsonPlaceholderAPI::class.java)


        val postsCall = api.getPosts()
        //getPost(postsCall)

        val commentsCall = api.getPostComments(1)
        getComments(commentsCall)


        val post = PostModel(
            0,
            "",
            "",
            3
        )

        val sendPost = api.createPost(post)
        getSendPostResults(sendPost)


    }

    private fun getSendPostResults(sendPost: Call<PostModel>) {
        sendPost.enqueue(object : Callback<PostModel> {
            override fun onFailure(call: Call<PostModel>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, t?.message.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<PostModel>?, response: Response<PostModel>?) {
                if (response != null) {
                    if (response.isSuccessful) {

                    }
                } else {
                    Toast.makeText(this@MainActivity, "null", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun getComments(commentsCall: Call<List<PostComments>>) {
        commentsCall.enqueue(object : Callback<List<PostComments>> {
            override fun onFailure(call: Call<List<PostComments>>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, t?.message.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<PostComments>>?, response: Response<List<PostComments>>?) {
                if (response != null) {
                    if (response.isSuccessful) {
                        for (comment in response.body()) {

                        }
                        Toast.makeText(this@MainActivity, "success ${response.body().size}", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@MainActivity, "error", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "null", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun getPost(postsCall : Call<List<PostModel>>) {
        postsCall.enqueue(object : Callback<List<PostModel>> {
            override fun onFailure(call: Call<List<PostModel>>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, t?.message.toString(), Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<PostModel>>?, response: Response<List<PostModel>>?) {
                if (response != null) {
                    if (response.isSuccessful) {
                        posts = response.body()
                        for (post in posts) {
                            print(post.toString())
                        }
                        Toast.makeText(this@MainActivity, "success", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@MainActivity, response.code().toString(), Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "null", Toast.LENGTH_LONG).show()
                }

            }
        })
    }

}
