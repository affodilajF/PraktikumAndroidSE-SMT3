package com.example.tugaspertemuan9ya

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tugaspertemuan9ya.databinding.FragmentOrderticketBinding
import com.example.tugaspertemuan9ya.databinding.FragmentTicketBinding

class OrderticketFragment : Fragment() {

    private lateinit var binding : FragmentOrderticketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderticketBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            editTxtJenisticket.setOnClickListener(){
                val action = OrderticketFragmentDirections.actionOrderticketFragmentToChooseorderticketFragment();
                findNavController().navigate(action);
            }

            findNavController().currentBackStackEntry?.savedStateHandle?.let{
                    handle ->
                handle.getLiveData<String>("address").
                observe(viewLifecycleOwner){
                        res ->
                    editTxtJenisticket.setText(res)
                }
            }
        }


    }

}