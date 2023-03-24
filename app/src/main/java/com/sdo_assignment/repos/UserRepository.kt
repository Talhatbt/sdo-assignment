package com.sdo_assignment.repos

import com.sdo_assignment.network.ApiInterface
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiInterface: ApiInterface) {
    fun fetchUsers() = apiInterface.getUserDetails()
}