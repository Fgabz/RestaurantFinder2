package com.fanilo.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fanilo.android.IDaggerFactoryViewModel
import com.fanilo.android.extension.createViewControllerWith
import com.fanilo.android.extension.observe
import com.fanilo.android.extension.with
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
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import com.mapbox.mapboxsdk.utils.BitmapUtils
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.map_fragment.*
import javax.inject.Inject

class MapFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: IDaggerFactoryViewModel

    private lateinit var viewController: MapViewController

    private lateinit var mapboxMap: MapboxMap
    private lateinit var symbolManager: SymbolManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewController = createViewControllerWith(viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.map_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapView?.onCreate(savedInstanceState)
        viewController.onViewCreated(savedInstanceState)

        observe(viewController.liveDataRestaurantViewState) with { markerList ->
            val option = SymbolOptions()
            for (marker in markerList) {
                symbolManager.create(
                    option
                        .withLatLng(LatLng(marker.latLng.first, marker.latLng.second))
                        .withIconImage(ICON_ID)
                        .withTextField(marker.restaurantName)
                )
            }
        }
    }

    fun initMap() {
        mapView.getMapAsync { mapBox ->
            this.mapboxMap = mapBox
            mapBox.setStyle(Style.MAPBOX_STREETS) {
                BitmapUtils.getBitmapFromDrawable(resources.getDrawable(R.drawable.mapbox_marker_icon_default))?.let { it1 ->
                    it.addImage(
                        ICON_ID,
                        it1
                    )
                }

                symbolManager = SymbolManager(mapView, mapboxMap, it)

                if (PermissionsManager.areLocationPermissionsGranted(requireContext())) {
                    enableLocationComponent(it)
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun enableLocationComponent(style: Style) {

        val customLocationComponentOptions = LocationComponentOptions.builder(requireContext())
            .trackingGesturesManagement(true)
            .build()

        val locationComponentActivationOptions = LocationComponentActivationOptions.builder(requireContext(), style)
            .locationComponentOptions(customLocationComponentOptions)
            .build()

        mapboxMap.locationComponent.apply {
            activateLocationComponent(locationComponentActivationOptions)

            isLocationComponentEnabled = true

            cameraMode = CameraMode.TRACKING
            renderMode = RenderMode.COMPASS

            lastKnownLocation?.let { location ->
                val cameraPosition = CameraPosition.Builder()
                    .target(LatLng(location.latitude, location.longitude))
                    .zoom(MAP_ZOOM_LEVEL)
                    .build()

                mapboxMap.cameraPosition = cameraPosition
                mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 1000)

                val bounds =
                    mapboxMap.getLatLngBoundsZoomFromCamera(cameraPosition)


                bounds?.latLngBounds?.let {
                    viewController.onMapReady(
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
        private const val ICON_ID = "ICON_ID"
        private const val MAP_ZOOM_LEVEL = 15.0
    }
}