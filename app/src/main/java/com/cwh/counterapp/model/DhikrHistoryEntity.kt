package com.cwh.counterapp.model

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "dhikr_history")
data class DhikrHistoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dhikrId: String,
    val dhikrName: String,
    val count: Int,
    val timestamp: Long
)