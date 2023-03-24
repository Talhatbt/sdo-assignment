package com.sdo_assignment.utils

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.sdo_assignment.adapters.LastAdapter
import com.sdo_assignment.utils.extensions.getParentActivity

/**
 * Binding widgets and do some functionality according to requirements
 */
object BindingAdapters {

    @BindingAdapter("app:items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, list: List<Any>) {
        with(recyclerView.adapter as LastAdapter<Any>) {
            items = list
        }
    }

    @BindingAdapter("app:goneUnless")
    @JvmStatic
    fun setGoneUnless(view: View, visibility: ObservableField<Int>?) {
        view.visibility  = visibility?.get() ?: View.VISIBLE
    }
}
