package br.com.kbat.educamat

import android.app.Application
import br.com.kbat.educamat.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class EducaMatApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@EducaMatApplication)
            modules(appModule)
        }
    }

}