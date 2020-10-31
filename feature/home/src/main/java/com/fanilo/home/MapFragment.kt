package com.fanilo.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.fanilo.android.IDaggerFactoryViewModel
import com.fanilo.entity.LatitudeLongitude
import com.fanilo.entity.LatitudeLongitudeBounds
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.map_fragment.*
import javax.inject.Inject

class MapFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: IDaggerFactoryViewModel

    private lateinit var viewController: MapViewController

    private lateinit var mapboxMap: MapboxMap

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewController = ViewModelProvider(this, viewModelFactory).get(MapViewController::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.map_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewController.onCreate()
        mapView?.onCreate(savedInstanceState)
    }

    fun initMap() {
        mapView?.getMapAsync { mapBox ->
            this.mapboxMap = mapBox
            mapBox.setStyle(Style.MAPBOX_STREETS) {
                if (PermissionsManager.areLocationPermissionsGranted(requireContext())) {
                    enableLocationComponent(it)
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun enableLocationComponent(style: Style) {
// Create and customize the LocationComponent's options
        val customLocationComponentOptions = LocationComponentOptions.builder(requireContext())
            .trackingGesturesManagement(true)
            .build()

        val locationComponentActivationOptions = LocationComponentActivationOptions.builder(requireContext(), style)
            .locationComponentOptions(customLocationComponentOptions)
            .build()

        // Get an instance of the LocationComponent and then adjust its settings
        mapboxMap.locationComponent.apply {
            // Activate the LocationComponent with options
            activateLocationComponent(locationComponentActivationOptions)

            // Enable to make the LocationComponent visible
            isLocationComponentEnabled = true

            // Set the LocationComponent's camera mode
            cameraMode = CameraMode.TRACKING

            // Set the LocationComponent's render mode
            renderMode = RenderMode.COMPASS

            lastKnownLocation?.let { location ->
                val position = CameraPosition.Builder()
                    .target(LatLng(location.latitude, location.longitude))
                    .zoom(11.0)
                    .build()

                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 1000)

                mapboxMap.getLatLngBoundsZoomFromCamera(mapboxMap.cameraPosition).latLngBounds?.let {
                    viewController.onViewReady(
                        LatitudeLongitudeBounds(it.latNorth, it.latSouth, it.lonEast, it.lonWest),
                        LatitudeLongitude(location.latitude, location.longitude)
                    )
                }
            }

        }
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    companion object {
        fun newInstance() = MapFragment()
    }
}