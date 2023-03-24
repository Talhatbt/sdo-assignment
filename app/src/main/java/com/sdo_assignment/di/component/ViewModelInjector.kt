package com.sdo_assignment.di.component

import com.sdo_assignment.di.module.NetworkModule
import com.sdo_assignment.ui.UserListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject().
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified ArticleListViewModel.
     * @param userListViewModel in which to inject the dependencies
     */
    fun inject(userListViewModel: UserListViewModel)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}