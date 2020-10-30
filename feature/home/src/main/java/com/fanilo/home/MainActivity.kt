package com.fanilo.home

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.container)

        if (currentFragment == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, MapFragment.newInstance())
            }.commit()
        }

    }
}