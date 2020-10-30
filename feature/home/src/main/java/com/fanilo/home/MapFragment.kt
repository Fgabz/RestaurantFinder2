package com.fanilo.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.fanilo.android.IDaggerFactoryViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MapFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: IDaggerFactoryViewModel

    private lateinit var viewController: MapViewController

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
    }

    companion object {
        fun newInstance() = MapFragment()
    }
}