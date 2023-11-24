package com.example.noteapp.DataFlow

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideDao(noteDatabase: NoteDatabase): NoteDao = noteDatabase.getDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase
    = Room.databaseBuilder(context, NoteDatabase::class.java,"NoteDatabase")
        .fallbackToDestructiveMigration()
        .build()
}