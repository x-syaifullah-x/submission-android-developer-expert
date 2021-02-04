package com.android.developer.expert

import com.android.developer.expert.core.di.component.CoreComponent
import com.android.developer.expert.core.di.component.DaggerCoreComponent
import com.android.developer.expert.di.component.AppComponent
import com.android.developer.expert.di.component.DaggerAppComponent
import com.google.android.play.core.splitcompat.SplitCompatApplication

class App : SplitCompatApplication() {
    val dataComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(dataComponent)
    }
}