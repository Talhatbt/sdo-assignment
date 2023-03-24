package com.sdo_assignment.ui

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.sdo_assignment.R
import com.sdo_assignment.base.BaseViewModel
import com.sdo_assignment.model.UserDetailResponse
import com.sdo_assignment.repos.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserListViewModel : BaseViewModel() {

    @Inject
    lateinit var userRepo: UserRepository

    val errorClickListener = View.OnClickListener { loadUsers() }
    val loadingVisibility = ObservableField<Int>().apply { 0 }
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    var usersList: MutableLiveData<List<UserDetailResponse>> = MutableLiveData()

    private lateinit var subscription: Disposable

    init {
        loadUsers()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadUsers() {
        subscription =
            userRepo.fetchUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievalStart() }
                .doOnTerminate { onRetrievalFinish() }
                .subscribe({ result -> onRetrieveUserSuccess(result as List<UserDetailResponse>) },
                    { error ->
                        onRetrieveUserError()
                    }
                )
    }

    private fun onRetrievalStart() {
        loadingVisibility.set(View.VISIBLE)
    }

    private fun onRetrievalFinish() {
        loadingVisibility.set(View.GONE)
    }

    private fun onRetrieveUserSuccess(articleList: List<UserDetailResponse>) {
        usersList.postValue(articleList)
    }

    private fun onRetrieveUserError() {
        errorMessage.postValue(R.string.post_error)
    }
}