package com.fanilo.android

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

abstract class ViewController : ViewModel() {

    private var onViewReadyCalled = false
    private var onCreateCalled = false

    override fun onCleared() {
        super.onCleared()
        onViewFinished()
    }

    fun onViewControllerCreated() {
        if (!onCreateCalled) {
            onCreate()
            onCreateCalled = true
        }
    }

    fun onViewCreated(extras: Bundle?) {
        if (!onViewReadyCalled) {
            onViewReady(extras)
            onViewReadyCalled = true
        }
    }

    /**
     * Called only once the ViewController has been created.
     * Should be synchronous (no coroutines)
     */
    @MainThread
    open fun onCreate() = Unit

    /**
     * Called only once the attached view is ready (has been passed by ON_CREATE).
     * If the view has been restored (by a screen rotation for instance), it won't be called again.
     */
    @MainThread
    open fun onViewReady(extras: Bundle?): Job? = null

    /**
     * Called when the ViewController has been cleared.
     */
    @MainThread
    open fun onViewFinished() = Unit
}