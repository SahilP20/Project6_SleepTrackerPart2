package com.example.project5_sleeptracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sleep_table")
data class SleepEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "hours_slept") val hoursSlept: Double,
    @ColumnInfo(name = "quality_rating") val qualityRating: Int,
    @ColumnInfo(name = "notes") val notes: String? = null
)
