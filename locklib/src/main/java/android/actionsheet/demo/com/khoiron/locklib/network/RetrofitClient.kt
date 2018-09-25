package com.unicode.kingmarket.Utility.NetworkRepo

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by khoiron on 19/01/18.
 */
object RetrofitClient {

    var retrofit: Retrofit? = null

    fun getClient(BaseUrl: String): Retrofit? {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(logging).build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }
}