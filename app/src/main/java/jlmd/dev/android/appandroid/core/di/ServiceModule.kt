package jlmd.dev.android.appandroid.core.di

import jlmd.dev.android.appandroid.core.service.PhotoApi
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {

    single<PhotoApi> {
        provideService(get(named(RetrofitClients.PHOTOS_API.name)))
    }
}

private inline fun <reified T> provideService(retrofit: Retrofit): T = retrofit.create(T::class.java)
