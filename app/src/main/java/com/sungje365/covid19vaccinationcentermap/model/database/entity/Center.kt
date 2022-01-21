package com.sungje365.covid19vaccinationcentermap.model.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Center(
    @PrimaryKey val id: Int,
    val centerName: String,
    val sido: String,
    val sigungu: String,
    val facilityName: String,
    val zipCode: String,
    val address: String,
    val lat: String,
    val lng: String,
    val createdAt: String,
    val updatedAt: String,
    val centerType: String,
    val org: String,
    val phoneNumber: String
)