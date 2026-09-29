package com.aiansweringmachine.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CallDao {
    @Query("SELECT * FROM call_summaries ORDER BY createdAtEpochMillis DESC")
    fun observeAll(): Flow<List<CallSummaryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(summary: CallSummaryEntity)

    @Query("DELETE FROM call_summaries")
    suspend fun deleteAll()
}
