package com.sungje365.covid19vaccinationcentermap.ui.map

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay
import com.naver.maps.map.util.MarkerIcons
import com.sungje365.covid19vaccinationcentermap.R
import com.sungje365.covid19vaccinationcentermap.databinding.FragmentMapBinding
import com.sungje365.covid19vaccinationcentermap.ui.main.SharedViewModel

class MapFragment : Fragment(), OnMapReadyCallback {
    private lateinit var naverMap: NaverMap
    private lateinit var binding: FragmentMapBinding
    private val model: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false)

        binding.apply {
            viewModel = model
            lifecycleOwner = this@MapFragment
            mapView.onCreate(savedInstanceState)
            mapView.getMapAsync(this@MapFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mapView.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    override fun onMapReady(p0: NaverMap) {
        naverMap = p0
        val centers = model.getAll()

        centers.observe(this, Observer {
            for (center in it) {
                Marker().apply {
                    position = LatLng(center.lat.toDouble(), center.lng.toDouble())
                    icon = MarkerIcons.BLACK
                    iconTintColor = when (center.centerType) {
                        "중앙/권역" -> Color.RED
                        "지역" -> Color.BLUE
                        else -> Color.GREEN
                    }
                    onClickListener = Overlay.OnClickListener {
                        AlertDialog.Builder(context).apply {
                            setTitle(center.centerName)
                            setMessage(
                                "주소: ${center.address} ${center.facilityName}\n" +
                                        "우편번호: ${center.zipCode}\n" +
                                        "연락처: ${center.phoneNumber}\n"
                            )
                            setPositiveButton("OK") { _, _ -> }
                        }.show()

                        false
                    }
                    map = naverMap
                }
            }
        })
    }

}