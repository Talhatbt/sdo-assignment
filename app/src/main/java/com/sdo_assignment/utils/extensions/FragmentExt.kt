package com.sdo_assignment.utils.extensions

import androidx.annotation.AnimRes
import androidx.fragment.app.Fragment


fun Fragment.popStack() = activity?.supportFragmentManager?.popBackStack()

fun Fragment.replaceFragment(
    containerId: Int,
    fragment: Fragment,
    addToStack: Boolean = false,
    @AnimRes animRes: Array<Int>? = null
) =

    activity?.supportFragmentManager?.transact {
        if (animRes != null)
            if (animRes.size == 2)
                setCustomAnimations(animRes[0], animRes[1])
            else
                setCustomAnimations(animRes[0], animRes[1], animRes[2], animRes[3])
        replace(containerId, fragment, fragment.javaClass.name)
        if (addToStack)
            addToBackStack(fragment.javaClass.name)
    }


/**
 * add fragment
 * containerId -> fragment inflation container id
 * fragment -> fragment to be inflated
 * addToStack -> flag whether to add fragment to back stack
 * animRes -> fragment in or out animation
 */
fun Fragment.addFragment(
    containerId: Int,
    fragment: Fragment,
    addToStack: Boolean = true,
    @AnimRes animRes: Array<Int>? = null
) =

    activity?.supportFragmentManager?.transact {
        if (animRes != null)
            if (animRes.size == 2)
                setCustomAnimations(animRes[0], animRes[1])
            else
                setCustomAnimations(animRes[0], animRes[1], animRes[2], animRes[3])
        add(containerId, fragment, fragment.javaClass.name)
        if (addToStack)
            addToBackStack(fragment.javaClass.name)
    }

/**
 * fragment in and out fade animation
 */
@AnimRes
fun getFadeInOutAnimation() =
    arrayOf(
        android.R.anim.fade_in,
        android.R.anim.fade_out,
        android.R.anim.fade_in,
        android.R.anim.fade_out
    )