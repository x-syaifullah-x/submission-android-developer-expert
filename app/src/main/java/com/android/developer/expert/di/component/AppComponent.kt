package com.android.developer.expert.di.component

import com.android.developer.expert.core.di.component.CoreComponent
import com.android.developer.expert.di.annotation.AppScope
import com.android.developer.expert.di.module.ViewModelModule
import com.android.developer.expert.presentation.detail.DetailActivity
import com.android.developer.expert.presentation.movie.MovieFragment
import com.android.developer.expert.presentation.search.SearchActivity
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [ViewModelModule::class]
)

interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(dataComponent: CoreComponent): AppComponent
    }

    fun inject(activity: DetailActivity)

    fun inject(activity: SearchActivity)

    fun inject(fragment: MovieFragment)

}