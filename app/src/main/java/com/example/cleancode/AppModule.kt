package com.example.cleancode

import android.content.Context
import com.example.cleancode.db.HelperDataBase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = HelperDataBase.getDatabase(context)

    @Singleton
    @Provides
    fun personajeDao(db: HelperDataBase) = db.personajeDao()


    @Singleton
    @Provides
    fun retrofit(baseUrl: String) : Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}