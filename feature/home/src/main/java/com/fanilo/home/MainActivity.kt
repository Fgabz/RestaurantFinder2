package com.fanilo.home

import android.os.Bundle
import android.widget.Toast
import com.fanilo.core.annotation.MapboxPublicToken
import com.fanilo.domain.IPreference
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.Mapbox
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), PermissionsListener {

    @Inject
    @MapboxPublicToken
    lateinit var mapboxToken: IPreference<String>

    private var permissionsManager: PermissionsManager = PermissionsManager(this)

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

        permissionsManager.requestLocationPermissions(this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onExplanationNeeded(permissionsToExplain: List<String>) {
        Toast.makeText(this, "Location is need to be able to use the app", Toast.LENGTH_LONG).show()
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            (supportFragmentManager.findFragmentById(R.id.container) as? MapFragment)?.initMap()
        } else {
            Toast.makeText(this, "Location refused, the app will stop", Toast.LENGTH_LONG).show()
            finish()
        }
    }
}