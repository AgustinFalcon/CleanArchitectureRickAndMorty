package com.example.cleancode.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.cleancode.R
import com.example.cleancode.databinding.FragmentLogInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : Fragment() {

    private var _binding : FragmentLogInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLogInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_homeFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LogInFragment()
        const val TAG = "LogInFragment"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}