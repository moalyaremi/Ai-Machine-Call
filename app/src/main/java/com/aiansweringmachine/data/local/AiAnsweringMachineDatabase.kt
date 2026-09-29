package com.aiansweringmachine.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [CallSummaryEntity::class, CallerRuleEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class AiAnsweringMachineDatabase : RoomDatabase() {
    abstract fun callDao(): CallDao
    abstract fun callerRuleDao(): CallerRuleDao
}
