package com.fanilo.android.extension

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.fanilo.android.ViewController

inline fun <reified V : ViewController> ViewModelStoreOwner.createViewControllerWith(factory: ViewModelProvider.Factory): V =
    ViewModelProvider(this, factory)[V::class.java].apply {
        onViewControllerCreated()
    }