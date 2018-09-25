package com.unicode.kingmarket.Utility.NetworkRepo

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by khoiron on 19/01/18.
 */
interface URLunicode {

    /*@FormUrlEncoded
    @POST("password/forget")
    abstract fun getVeriviCode(@Field("value") version: String): Call<ResponseBody>*/
    @POST("password/send-code")
    abstract fun getVeriviCode(@Field("code") code: String,
                               @Field("value") value: String): Call<ResponseBody>

    //082231456179
}
