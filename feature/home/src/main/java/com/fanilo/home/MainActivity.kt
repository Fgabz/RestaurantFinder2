package com.fanilo.home

import android.os.Bundle
import com.fanilo.core.annotation.MapboxPublicToken
import com.fanilo.domain.IPreference
import com.mapbox.mapboxsdk.Mapbox
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    @MapboxPublicToken
    lateinit var mapboxToken: IPreference<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Mapbox.getInstance(this, mapboxToken.get())

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.container)

        if (currentFragment == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.container, MapFragment.newInstance())
            }.commit()
        }
    }
}