package com.aiansweringmachine.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CallerRuleDao {
    @Query("SELECT * FROM caller_rules WHERE isEnabled = 1 ORDER BY name")
    fun observeEnabled(): Flow<List<CallerRuleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rule: CallerRuleEntity)

    @Delete
    suspend fun delete(rule: CallerRuleEntity)
}
