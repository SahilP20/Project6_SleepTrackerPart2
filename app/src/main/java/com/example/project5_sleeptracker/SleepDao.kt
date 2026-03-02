package com.example.project5_sleeptracker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepDao {
    @Insert
    suspend fun insert(sleep: SleepEntry)

    @Update
    suspend fun update(sleep: SleepEntry)

    @Query("SELECT * FROM sleep_table ORDER BY id DESC")
    fun getAllSleepEntries(): Flow<List<SleepEntry>>

    @Query("DELETE FROM sleep_table")
    suspend fun clear()
}
