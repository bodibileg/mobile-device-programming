package com.example.animalkingdomexplorer.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.animalkingdomexplorer.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        binding.apply {
            btnAnimalDetails.setOnClickListener {
                val directions =
                    DashboardFragmentDirections.actionDashboardFragmentToAnimalDetailsFragment()
                findNavController().navigate(directions)
            }
            btnSpeciesDetails.setOnClickListener {
                val directions =
                    DashboardFragmentDirections.actionDashboardFragmentToSpeciesDetailsFragment()
                findNavController().navigate(directions)
            }
        }
        return binding.root
    }


}