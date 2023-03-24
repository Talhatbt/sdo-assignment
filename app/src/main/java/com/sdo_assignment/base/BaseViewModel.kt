package com.sdo_assignment.base

import androidx.lifecycle.ViewModel
import com.sdo_assignment.di.component.DaggerViewModelInjector
import com.sdo_assignment.di.component.ViewModelInjector
import com.sdo_assignment.di.module.NetworkModule
import com.sdo_assignment.ui.UserListViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is UserListViewModel -> injector.inject(this)
        }
    }
}