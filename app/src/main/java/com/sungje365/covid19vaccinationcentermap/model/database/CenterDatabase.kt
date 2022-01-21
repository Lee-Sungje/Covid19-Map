package com.sungje365.covid19vaccinationcentermap.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sungje365.covid19vaccinationcentermap.model.database.entity.Center
import com.sungje365.covid19vaccinationcentermap.model.database.dao.CenterDao

@Database(entities = [Center::class], version = 1)
abstract class CenterDatabase : RoomDatabase() {
    abstract fun centerDao(): CenterDao

    companion object {
        private var INSTANCE: CenterDatabase? = null

        fun getInstance(context: Context): CenterDatabase? {
            if (INSTANCE == null) {
                synchronized(CenterDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext, CenterDatabase::class.java, "center-db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}