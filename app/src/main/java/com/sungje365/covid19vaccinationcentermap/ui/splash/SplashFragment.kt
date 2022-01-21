package com.sungje365.covid19vaccinationcentermap.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sungje365.covid19vaccinationcentermap.R
import com.sungje365.covid19vaccinationcentermap.databinding.FragmentSplashBinding
import com.sungje365.covid19vaccinationcentermap.ui.main.SharedViewModel

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val model: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
        binding.lifecycleOwner = this@SplashFragment

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        model.search()
        findNavController().navigate(R.id.action_splashFragment_to_mapFragment)
    }

}