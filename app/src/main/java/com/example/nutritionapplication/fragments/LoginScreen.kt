package com.example.nutritionapplication.fragments

import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.example.nutritionapplication.MainActivity
import com.example.nutritionapplication.R
import com.example.nutritionapplication.databinding.FragmentLoginScreenBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginScreen : Fragment() {

    private var _binding: FragmentLoginScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginScreenBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        (activity as MainActivity).hideBottomNav()
        binding.btnLogin.setOnClickListener{
            findNavController().navigate(R.id.action_loginScreen_to_vitamins2)
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch(Dispatchers.IO) {
            val animation = TransitionInflater.from(requireContext()).inflateTransition(
                android.R.transition.move
            )
            sharedElementEnterTransition = animation
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding= null

    }
}