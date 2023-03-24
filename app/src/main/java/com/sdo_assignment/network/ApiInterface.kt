package com.sdo_assignment.network

import com.sdo_assignment.model.UserDetailResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface ApiInterface {
    /**
     * Get the list of the users from the API
     */
    @GET("/users")
    fun getUserDetails(): Observable<List<UserDetailResponse>>
}