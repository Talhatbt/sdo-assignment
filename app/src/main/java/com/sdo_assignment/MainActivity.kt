package com.sdo_assignment

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.sdo_assignment.adapters.LastAdapter
import com.sdo_assignment.di.ViewModelFactory
import com.sdo_assignment.databinding.ActivityMainBinding
import com.sdo_assignment.model.UserDetailResponse
import com.sdo_assignment.ui.UserListViewModel
import com.sdo_assignment.utils.extensions.replaceFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var errorSnackbar: Snackbar? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserListViewModel

    private lateinit var userListAdapter: LastAdapter<UserDetailResponse>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        init()
        setObservers()
        setAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    private fun init() {
        viewModel =
            ViewModelProviders.of(this, ViewModelFactory())[UserListViewModel::class.java]

        binding.layoutUserList.viewmodel = viewModel
    }

    private fun setAdapter() {

        // initialize adapter
        userListAdapter = LastAdapter(
            R.layout.layout_row_user,
            object : LastAdapter.OnItemClickListener<UserDetailResponse> {
                override fun onItemClick(item: UserDetailResponse) {

                    replaceFragment(
                        R.id.container, UserDetailFragment.newInstance(item), true,
                        arrayOf(R.anim.enter_from_right, R.anim.exit_to_left )
                    )
                }
            })

        binding.layoutUserList.rvUsers.adapter = userListAdapter
    }

    // setup observers
    private fun setObservers() {
        viewModel.usersList.observe(this) {
            if (it.isNotEmpty()) {
                Log.e("user", it.toString())
                userListAdapter.items = it
            }
        }

        viewModel.errorMessage.observe(this) {
            if (it != null)
                showError(it) else hideError()
        }
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}