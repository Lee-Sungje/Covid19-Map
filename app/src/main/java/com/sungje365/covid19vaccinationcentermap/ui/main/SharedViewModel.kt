package com.sungje365.covid19vaccinationcentermap.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.sungje365.covid19vaccinationcentermap.BuildConfig
import com.sungje365.covid19vaccinationcentermap.model.database.repository.CenterRepository
import com.sungje365.covid19vaccinationcentermap.model.database.entity.Center
import com.sungje365.covid19vaccinationcentermap.model.external.RetrofitBuilder
import com.sungje365.covid19vaccinationcentermap.model.external.response.CentersResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SharedViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CenterRepository(application)
    private val centers = repository.getAll()

    fun insert(center: Center) {
        repository.insert(center)
    }

    fun insertAll(centers: List<Center>) {
        repository.insertAll(centers)
    }

    fun delete(center: Center) {
        repository.delete(center)
    }

    fun getAll(): LiveData<List<Center>> {
        return centers
    }

    fun search() {
        for (pageNumber in 1..10) {
            searchPage(pageNumber.toString())
        }
    }

    private fun searchPage(pageNumber: String) {
        val call = RetrofitBuilder.centersApi.getCenters(BuildConfig.API_KEY, pageNumber)

        call.enqueue(object : Callback<CentersResponse> {
            override fun onResponse(
                call: Call<CentersResponse>,
                response: Response<CentersResponse>
            ) {
                CoroutineScope(Dispatchers.IO).launch {
                    response.body()?.data
                        ?.map {
                            Center(
                                id = it.id,
                                centerName = it.centerName,
                                sido = it.sido,
                                sigungu = it.sigungu,
                                facilityName = it.facilityName,
                                zipCode = it.zipCode,
                                address = it.address,
                                lat = it.lat,
                                lng = it.lng,
                                createdAt = it.createdAt,
                                updatedAt = it.updatedAt,
                                centerType = it.centerType,
                                org = it.org,
                                phoneNumber = it.phoneNumber
                            )
                        }
                        ?.let {
                            insertAll(it)
                        }
                }
            }

            override fun onFailure(call: Call<CentersResponse>, t: Throwable) {
                Log.d("fail", t.message.toString())
            }
        })
    }
}