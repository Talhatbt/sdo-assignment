package com.sdo_assignment.utils.extensions

import android.content.Intent
import androidx.annotation.AnimRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * Activity Extension functions
 */

/**
 * added extension function to AppCompatActivity that opens new activity
 * isFinishCurrentActivity -> flag to close the previous activity
 * clazz -> target opening activity
 */
fun <T : AppCompatActivity> AppCompatActivity.openActivity(
    isFinishCurrentActivity: Boolean,
    clazz: Class<T>
) {
    if (isFinishCurrentActivity) finish()
    val intent = Intent(this, clazz)
    startActivity(intent)
}

/**
 * replace fragment
 * containerId -> fragment inflation container id
 * fragment -> fragment to be inflated
 * addToStack -> flag whether to add fragment to back stack
 * animRes -> fragment in or out animation
 */
fun AppCompatActivity.replaceFragment(
    containerId: Int,
    fragment: Fragment,
    addToStack: Boolean = false,
    @AnimRes animRes: Array<Int>? = null
) =

    supportFragmentManager.transact {
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
fun AppCompatActivity.addFragment(
    containerId: Int,
    fragment: Fragment,
    addToStack: Boolean = true,
    @AnimRes animRes: Array<Int>? = null
) =

    supportFragmentManager.transact {
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
 * fragment transaction
 */
inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commitAllowingStateLoss()
}

/**
 * remove top fragment from stack
 */
fun AppCompatActivity.popStack() = supportFragmentManager.popBackStack()

/**
 * remove all fragment from stack of current activity
 */
fun AppCompatActivity.popAllStack() =
    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

/**
 * check whether any fragment is in stack or not
 * @return true/false
 */
fun AppCompatActivity.isStack() = supportFragmentManager?.backStackEntryCount!! > 0

/**
 * fragment in slide animation
 */
//@AnimRes
//fun AppCompatActivity.getInAnimation() =
//    arrayOf(R.anim.enter_from_right, R.anim.exit_to_left)
//
///**
// * fragment in and out slide animation
// */
//@AnimRes
//fun AppCompatActivity.getInOutAnimation() =
//    arrayOf(
//        R.anim.enter_from_right,
//        R.anim.exit_to_left,
//        R.anim.enter_from_left,
//        R.anim.exit_to_right
//    )

/**
 * fragment in and out fade animation
 */
@AnimRes
fun AppCompatActivity.getFadeInOutAnimation() =
    arrayOf(
        android.R.anim.fade_in,
        android.R.anim.fade_out,
        android.R.anim.fade_in,
        android.R.anim.fade_out
    )