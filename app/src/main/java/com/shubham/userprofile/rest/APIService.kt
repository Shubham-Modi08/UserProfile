package com.shubham.userprofile.rest

import com.shubham.userprofile.model.Response
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("api")    //End Url
    fun fetchUserDetails(): Call<Response>
}