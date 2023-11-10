package jlmd.dev.android.appandroid.core.di

import jlmd.dev.android.appandroid.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { MainViewModel(get()) }
}