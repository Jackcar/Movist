package com.jacksonueda.movist

import android.app.Application
import com.jacksonueda.movist.di.component.AppComponent
import com.jacksonueda.movist.di.component.DaggerAppComponent
import com.jacksonueda.movist.di.module.AppModule
import com.jacksonueda.movist.di.module.DataModule
import com.orhanobut.hawk.Hawk
import uk.co.chrisjenx.calligraphy.CalligraphyConfig


/**
 * Created by Jackson on 11/07/17.
 */
class App : Application() {

    companion object {
        lateinit var mAppComponent: AppComponent
        fun appComponent() = mAppComponent
    }


    override fun onCreate() {
        super.onCreate()

        setupDagger()
        setupSharedPreferences()
        setupFonts()
    }

    // --------------------------------------------------------------------------------------------
    // SETUP
    // --------------------------------------------------------------------------------------------

    private fun setupDagger() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .dataModule(DataModule())
                .build()
    }

    private fun setupSharedPreferences() {
        Hawk.init(baseContext).build()
    }

    private fun setupFonts() {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Montserrat-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build())
    }

    // --------------------------------------------------------------------------------------------
    // HELPER
    // --------------------------------------------------------------------------------------------

}