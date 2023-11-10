package jlmd.dev.android.appandroid.core.di

import jlmd.dev.android.appandroid.core.PhotoRepository
import org.koin.dsl.module

val repositoriesModule = module {

    single { PhotoRepository(get()) }

}