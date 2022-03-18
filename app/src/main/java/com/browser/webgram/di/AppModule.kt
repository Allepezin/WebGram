package com.browser.webgram.di

import android.app.Application
import android.content.Context
import com.browser.webgram.preferences.AppPreference
import com.browser.webgram.preferences.AppPreferenceImpl
import com.browser.webgram.ui.activities.PreviewActivity
import com.browser.webgram.ui.main.MainFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {
    @Binds
    abstract fun bindContext(application: Application): Context

    @Binds
    abstract fun bindSharedPreferences(appPreferenceImpl: AppPreferenceImpl): AppPreference

    @ContributesAndroidInjector
    abstract fun previewActivityInjector(): PreviewActivity

    @ContributesAndroidInjector
    abstract fun mainFragmentInjector(): MainFragment
}
