//package com.sdo_assignment
//
//import android.annotation.SuppressLint
//import android.app.Activity
//import android.app.Application
//import android.content.Context
//import android.os.Bundle
//import com.sdo_assignment.daggar.component.ViewModelInjector
//import javax.inject.Inject
//
//class SdoApp : Application.ActivityLifecycleCallbacks, HasActivityInjector {
//
//    var basicComponent: ViewModelInjector? = null
//
//    /**
//     * Getter method for current activity
//     *
//     * @return Current Activity instance
//     */
//    var currentActivity: Activity? = null
//        private set
//
//    @JvmField
//    @Inject
//    var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null
//    override fun onCreate() {
//        super.onCreate()
//        if (application == null) {
//            application = this
//        }
//
//        basicComponent = ViewModelInjector.Builder.build()
//
//        basicComponent?.inject(this)
//    }
//
//    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
//    }
//
//    override fun onActivityStarted(p0: Activity) {
//    }
//
//    override fun onActivityResumed(p0: Activity) {
//    }
//
//    override fun onActivityPaused(p0: Activity) {
//    }
//
//    override fun onActivityStopped(p0: Activity) {
//    }
//
//    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
//    }
//
//    override fun onActivityDestroyed(p0: Activity) {
//    }
//
//    companion object {
//        @SuppressLint("StaticFieldLeak")
//        var application: SdoApp? = null
//            private set
//
//        @JvmStatic
//        val context: Context
//            get() = if (application!!.currentActivity == null) application?.applicationContext else application!!.currentActivity!!
//    }
//}