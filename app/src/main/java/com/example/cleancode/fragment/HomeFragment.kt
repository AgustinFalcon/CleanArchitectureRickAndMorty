package com.example.cleancode.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleancode.adapter.PersonajeAdapter
import com.example.cleancode.databinding.FragmentHomeBinding
import com.example.cleancode.network.retrofit.NetworkResult
import com.example.cleancode.repository.personaje.Personaje
import com.example.cleancode.viewmodels.PersonajeStateEvent
import com.example.cleancode.viewmodels.PersonajeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {


    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val personajeViewModel : PersonajeViewModel by viewModels()
    private lateinit var personajeAdapter: PersonajeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        suscribeObservers()
        setUpRecyclerView()
        personajeViewModel.setPjStateEvent(PersonajeStateEvent.GetPersonajeEvent)


        return binding.root
    }

    override fun onStart() {
        super.onStart()
        //Combinar los estados de repository con los estados de UI
    }

    private fun suscribeObservers() {
        personajeViewModel.pjDataState.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success<List<Personaje>> -> {
                    displayProgressBar(false)
                    personajeAdapter.personajes = it.data
                }
                is NetworkResult.Error -> {
                    displayProgressBar(false)

                }
                is NetworkResult.Loading -> {
                    displayProgressBar(true)
                }
            }
        }
    }


    private fun displayProgressBar(isDisplayed: Boolean) {
        binding.progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun setUpRecyclerView() {
        personajeAdapter = PersonajeAdapter()
        binding.rvFragmentHome.apply {
            adapter = personajeAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
        const val TAG = "HomeFragment"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}