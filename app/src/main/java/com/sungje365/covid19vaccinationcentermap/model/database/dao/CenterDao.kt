package com.sungje365.covid19vaccinationcentermap.model.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sungje365.covid19vaccinationcentermap.model.database.entity.Center

@Dao
interface CenterDao {
    @Query("SELECT * FROM Center ORDER BY id ASC")
    fun getAll(): LiveData<List<Center>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(center: Center)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(centers: List<Center>)

    @Delete
    fun delete(center: Center)
}