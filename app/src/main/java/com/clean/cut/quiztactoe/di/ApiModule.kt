package com.clean.cut.quiztactoe.di

import com.clean.cut.quiztactoe.BuildConfig
import com.clean.cut.quiztactoe.data.network.ApiResourceAdapter
import com.clean.cut.quiztactoe.data.network.OpenTdbApiClient
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val apiModule = module {
  fun provideMoshi(): Moshi {
    return Moshi.Builder()
      .add(ApiResourceAdapter())
      .build()
  }

  fun provideHttpClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
      val interceptor = HttpLoggingInterceptor()
      interceptor.level = HttpLoggingInterceptor.Level.BODY
      httpClient.addInterceptor(interceptor)
    }
    return httpClient.build()
  }

  fun provideRetrofit(moshi: Moshi, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .baseUrl("https://opentdb.com/")
      .client(client)
      .addConverterFactory(MoshiConverterFactory.create(moshi).withNullSerialization())
      .build()
  }

  fun provideApi(retrofit: Retrofit): OpenTdbApiClient {
    return retrofit.create(OpenTdbApiClient::class.java)
  }

  single { provideMoshi() }
  single { provideHttpClient() }
  single { provideRetrofit(get(), get()) }
  single { provideApi(get()) }
}
