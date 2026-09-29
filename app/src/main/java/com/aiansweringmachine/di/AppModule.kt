package com.aiansweringmachine.di

import android.content.Context
import androidx.room.Room
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.aiansweringmachine.data.local.AiAnsweringMachineDatabase
import com.aiansweringmachine.data.repository.DataStoreAssistantPreferencesRepository
import com.aiansweringmachine.data.repository.RoomCallHistoryRepository
import com.aiansweringmachine.data.repository.RoomCallerRuleRepository
import com.aiansweringmachine.domain.repository.AssistantPreferencesRepository
import com.aiansweringmachine.domain.repository.CallerRuleRepository
import com.aiansweringmachine.domain.telecom.TelecomCapabilityChecker
import com.aiansweringmachine.platform.telecom.AndroidTelecomCapabilityChecker
import com.aiansweringmachine.domain.speech.TextToSpeechProvider
import com.aiansweringmachine.platform.speech.AndroidTextToSpeechProvider
import com.aiansweringmachine.domain.repository.CallHistoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePreferencesDataStore(@ApplicationContext context: Context) =
        PreferenceDataStoreFactory.create {
            context.preferencesDataStoreFile("app_preferences")
        }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AiAnsweringMachineDatabase =
        Room.databaseBuilder(
            context,
            AiAnsweringMachineDatabase::class.java,
            "ai_answering_machine.db"
        ).build()

    @Provides
    fun provideCallDao(database: AiAnsweringMachineDatabase) = database.callDao()

    @Provides
    fun provideCallerRuleDao(database: AiAnsweringMachineDatabase) = database.callerRuleDao()

    @Provides
    @Singleton
    fun provideCallHistoryRepository(impl: RoomCallHistoryRepository): CallHistoryRepository = impl

    @Provides
    @Singleton
    fun provideAssistantPreferencesRepository(
        impl: DataStoreAssistantPreferencesRepository
    ): AssistantPreferencesRepository = impl

    @Provides
    @Singleton
    fun provideCallerRuleRepository(impl: RoomCallerRuleRepository): CallerRuleRepository = impl

    @Provides
    @Singleton
    fun provideTelecomCapabilityChecker(impl: AndroidTelecomCapabilityChecker): TelecomCapabilityChecker = impl

    @Provides
    @Singleton
    fun provideTextToSpeechProvider(impl: AndroidTextToSpeechProvider): TextToSpeechProvider = impl
}
