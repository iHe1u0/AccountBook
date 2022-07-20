package com.imorning.accountbook.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }
}

object ActivityCollector {

    private val activities = ArrayList<Activity>()

    fun addActivity(activity: BaseActivity) {
        activities.add(activity)
    }

    fun removeActivity(activity: BaseActivity) {
        activities.remove(activity)
    }

    fun finish() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        activities.clear()
    }
}