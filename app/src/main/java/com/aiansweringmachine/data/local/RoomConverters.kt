package com.aiansweringmachine.data.local

import androidx.room.TypeConverter
import com.aiansweringmachine.domain.model.AnsweringMode
import com.aiansweringmachine.domain.model.CallerCategory
import com.aiansweringmachine.domain.model.Priority

class RoomConverters {
    @TypeConverter fun fromPriority(value: Priority): String = value.name
    @TypeConverter fun toPriority(value: String): Priority = Priority.valueOf(value)
    @TypeConverter fun fromCategory(value: CallerCategory): String = value.name
    @TypeConverter fun toCategory(value: String): CallerCategory = CallerCategory.valueOf(value)
    @TypeConverter fun fromAnsweringMode(value: AnsweringMode): String = value.name
    @TypeConverter fun toAnsweringMode(value: String): AnsweringMode = AnsweringMode.valueOf(value)
}
