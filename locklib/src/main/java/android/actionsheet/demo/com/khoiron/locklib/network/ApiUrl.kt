package com.unicode.kingmarket.Utility.NetworkRepo

/**
 * Created by khoiron on 19/01/18.
 */
object ApiUrl {

    var BASE_URL = "http://13.251.205.142/api/"

    fun getData(): URLunicode {
        return RetrofitClient.getClient(BASE_URL)!!.create(URLunicode::class.java)
    }
}