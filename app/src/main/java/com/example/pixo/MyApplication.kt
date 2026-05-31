package com.example.pixo

import android.app.Application
import android.content.Context
import com.example.pixo.utils.LocaleManager

class MyApplication : Application() {

    override fun attachBaseContext(base: Context) {
        val savedLanguage = LocaleManager.getSavedLanguage(base)
        super.attachBaseContext(LocaleManager.setLocale(base, savedLanguage))
    }
}