package com.shubham.userprofile.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.shubham.userprofile.model.Response
import com.shubham.userprofile.rest.APIService
import com.shubham.userprofile.rest.RestClient
import retrofit2.Call
import retrofit2.Callback

class MainViewModel : ViewModel() {

    private val userDetails: MutableLiveData<Response> = MutableLiveData()
    val details: LiveData<Response> = Transformations.map(userDetails) { it }

    private var mApiService: APIService? = null


    fun fetchUserDetails() {
        mApiService = RestClient.client.create(APIService::class.java)
        val call = mApiService!!.fetchUserDetails();
        call.enqueue(object : Callback<Response> {

            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                userDetails.postValue(response.body()!!)
                Log.e("DATA",response.body().toString())

            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("ViewModel", "Got error : " + t.localizedMessage)
            }
        })
    }
}