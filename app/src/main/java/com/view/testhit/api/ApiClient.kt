package com.view.testhit.api


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    const val mTag: String = "ApiClient"
    private var retrofit: Retrofit? = null
    private var apiInterface: ApiInterface? = null

    var BASE_URL ="https://login.microsoftonline.com"

    private val client: Retrofit?
        get() {
            if (retrofit == null) {
                val httpLogger = HttpLoggingInterceptor()
                httpLogger.setLevel(HttpLoggingInterceptor.Level.BODY)

                val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(httpLogger)
                    .retryOnConnectionFailure(true)

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient.build())
                    .build()
            }
            return retrofit
        }


    fun getApiInterface(): ApiInterface? {
        if (apiInterface == null) {
            apiInterface = client?.create(ApiInterface::class.java)
        }
        return apiInterface
    }
}