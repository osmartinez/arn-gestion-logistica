package com.arneplant.logisticainterna_kot2.network

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class RetrofitInstance {
    companion object {
        private val BASE_URL = "http://192.168.0.104:3000/"
        private var retrofit: Retrofit? = null
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()


        var httpclient = OkHttpClient.Builder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(8, TimeUnit.SECONDS)
            .writeTimeout(8, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                var tryCount :Int = 0
                val maxTryCount = 15
                val request = chain.request()

                var error = true

                var response :Response? = null

                while(error && tryCount<maxTryCount){
                    try{
                        response = chain.proceed(request)
                        if(!response.isSuccessful){
                            throw TimeoutException("forzado")
                        }
                        else{
                            error  =false
                        }

                    }catch (ex:Exception){
                        Log.d("intercept", "Request is not successful or timedout - " + tryCount);
                        tryCount++

                    }
                }


                response!!
            }
            .build()


        fun getRetrofitInstance(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpclient)
                    .build()
            }
            return retrofit
        }
    }
}