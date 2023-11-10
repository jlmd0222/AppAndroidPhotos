package jlmd.dev.android.appandroid.core.di

import jlmd.dev.android.appandroid.core.usecases.GetPhotosUseCase
import org.koin.dsl.module

val useCaseModules = module {

    factory { GetPhotosUseCase(get()) }

}