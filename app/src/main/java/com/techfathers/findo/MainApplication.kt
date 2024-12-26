package com.techfathers.findo

import android.app.Application
import com.techeytech.rich_dad_poor_dad.data.preferences.PreferenceProvider
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class MainApplication : Application(), KodeinAware {

    override fun onCreate() {
        super.onCreate()
    }

    override val kodein = Kodein.lazy {

        import(androidXModule(this@MainApplication))
        bind() from singleton { PreferenceProvider(instance()) }
    }

}