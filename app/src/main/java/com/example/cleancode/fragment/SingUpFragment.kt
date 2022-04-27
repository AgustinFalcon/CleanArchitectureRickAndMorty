package com.example.cleancode.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleancode.R
import com.example.cleancode.databinding.FragmentSingUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingUpFragment : Fragment() {

    private var _binding : FragmentSingUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SingUpFragment()
        const val TAG = "SingUpFragment"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}