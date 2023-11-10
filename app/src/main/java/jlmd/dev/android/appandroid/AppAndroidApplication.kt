package jlmd.dev.android.appandroid

import android.app.Application
import jlmd.dev.android.appandroid.core.di.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

private val appModules = listOf<Module>() +
        viewModelsModule +
        networkModule +
        serviceModule +
        repositoriesModule +
        useCaseModules

class AppAndroidApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()

        initDI()
    }

    private fun initDI() {
        val applicationCoroutineScopeModule = module {
            single { applicationScope }
        }

        startKoin {
            androidContext(this@AppAndroidApplication)
            modules(
                appModules + applicationCoroutineScopeModule
            )
        }
    }
}