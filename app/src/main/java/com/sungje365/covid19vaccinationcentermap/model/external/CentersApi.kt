package com.sungje365.covid19vaccinationcentermap.model.external

import com.sungje365.covid19vaccinationcentermap.model.external.response.CentersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CentersApi {
    @GET("15077586/v1/centers?")
    fun getCenters(
        @Query("serviceKey") serviceKey: String,
        @Query("page") page: String,
        @Query("perPage") perPage: String = "10"
    ): Call<CentersResponse>
}