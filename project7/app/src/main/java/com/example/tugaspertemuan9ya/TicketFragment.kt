package com.example.tugaspertemuan9ya

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tugaspertemuan9ya.databinding.FragmentTicketBinding
import androidx.navigation.fragment.findNavController

class TicketFragment : Fragment() {

    private lateinit var binding : FragmentTicketBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTicketBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){

            btnBuy.setOnClickListener(){
                val action = TicketFragmentDirections.actionTicketFragmentToOrderticketFragment();
                findNavController().navigate(action);
            }

        }

    }



}