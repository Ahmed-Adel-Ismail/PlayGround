@file:Suppress("UNCHECKED_CAST")

package com.ahmedadel.kotlinapp.domain

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

/**
 * the parent class for all Model classes per application

 * Created by Ahmed Adel Ismail on 8/28/2017.
 */
open class Model : Fragment() {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }


    /**
     * clear the current [Model] from memory
     * @param activity the host [AppCompatActivity]
     */
    fun clear(activity: AppCompatActivity?) {
        if (activity != null && activity.isFinishing) {
            doRemoveAndClearSubClasses(activity)
        }
    }

    private fun doRemoveAndClearSubClasses(activity: AppCompatActivity) {
        val fm = activity.supportFragmentManager
        if (!fm.isDestroyed) {
            fm.beginTransaction().remove(this).commitAllowingStateLoss()
        }
        clear()
    }

    /**
     * override this method by sub-classes to clear the references held by this instance
     */
    protected open fun clear() {

    }

    companion object {

        /**
         * create an instance of the passed [Model] sub-class, if the instance already
         * exists, it will be returned back
         * @param activity the host [AppCompatActivity]
         * @param modelClass the [Class] of the sub-class of the [Model]
         * @param <T> the expected sub-class type
         * @return the valid [Model] instance
         * @throws RuntimeException if the initialization operation failed, make sure that all the
         * [Model] sub-classes have default no-args constructor
         * (or no declared constructors at all) </T> */
        @Throws(RuntimeException::class)
        fun <T : Model> of(activity: AppCompatActivity, modelClass: Class<T>): T? {
            val fm = activity.supportFragmentManager
            var viewModel: T? = fm?.findFragmentByTag(modelClass.name) as T?

            if (viewModel == null) {
                viewModel = createNewInstance(fm, modelClass)
            }

            return viewModel
        }

        private fun <T : Model> createNewInstance(fm: FragmentManager, modelClass: Class<T>): T? {
            val viewModel = constructModel(modelClass) as T
            fm.beginTransaction().add(viewModel, modelClass.name).commitAllowingStateLoss()
            return viewModel
        }

        private fun constructModel(modelClass: Class<*>): Any {
            val constructor = modelClass.getDeclaredConstructor()
            constructor.isAccessible = true
            return constructor.newInstance()
        }
    }


}