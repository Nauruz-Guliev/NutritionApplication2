@file:Suppress("DEPRECATION")

package com.example.nutritionapplication.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.nutritionapplication.MainActivity
import com.example.nutritionapplication.R
import com.example.nutritionapplication.databinding.FragmentSplashScreenBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class SplashScreen : Fragment() {

    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageViewLogo: ImageView
    private lateinit var textViewAppName: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).hideBottomNav()

        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedAnim()
    }

    private fun sharedAnim() {
        Handler(Looper.getMainLooper()).postDelayed({
            run {
                val extras = FragmentNavigatorExtras(
                    binding.imageViewLogo to "image_logo",
                    binding.appName to "text_logo2",
                    binding.appName to "text_logo1",
                    binding.linearLayout to "linear"
                )
                findNavController().navigate(
                    R.id.action_splashScreen_to_loginScreen, null, null, extras
                )
            }
        }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
