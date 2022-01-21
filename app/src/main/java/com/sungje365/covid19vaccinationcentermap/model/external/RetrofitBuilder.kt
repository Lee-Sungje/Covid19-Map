package com.sungje365.covid19vaccinationcentermap.model.external

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    var centersApi: CentersApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.odcloud.kr/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        centersApi = retrofit.create(CentersApi::class.java)
    }
}
