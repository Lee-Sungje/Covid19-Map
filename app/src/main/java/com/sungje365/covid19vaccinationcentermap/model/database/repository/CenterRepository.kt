package com.sungje365.covid19vaccinationcentermap.model.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.sungje365.covid19vaccinationcentermap.model.database.CenterDatabase
import com.sungje365.covid19vaccinationcentermap.model.database.dao.CenterDao
import com.sungje365.covid19vaccinationcentermap.model.database.entity.Center

class CenterRepository(application: Application) {
    private val centerDao: CenterDao
    private val centers: LiveData<List<Center>>

    init {
        val db = CenterDatabase.getInstance(application)
        centerDao = db!!.centerDao()
        centers = db.centerDao().getAll()
    }

    fun insert(center: Center) {
        centerDao.insert(center)
    }

    fun insertAll(centers: List<Center>) {
        centerDao.insertAll(centers)
    }

    fun delete(center: Center) {
        centerDao.delete(center)
    }

    fun getAll(): LiveData<List<Center>> {
        return centers
    }
}