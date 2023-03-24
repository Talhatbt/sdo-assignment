package com.sdo_assignment.di.module

import com.sdo_assignment.network.ApiInterface
import com.sdo_assignment.repos.UserRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object AppRepositoryModule {

    private const val NETWORK_CALL_TIMEOUT = 60

    /**
     * Provides the Article service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Article service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideArticleRepository(apiInterface: ApiInterface): UserRepository {
        return UserRepository(apiInterface)
    }
}