package com.example.tugaspertemuan9ya

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.tugaspertemuan9ya.databinding.FragmentChooseorderticketBinding
import android.widget.AdapterView
import androidx.navigation.fragment.findNavController

class ChooseorderticketFragment : Fragment() {

    private lateinit var binding: FragmentChooseorderticketBinding

    private lateinit var tickets: Array<String>

    private var ticketdipilih:String = "";



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


//        tickets = resources.getStringArray(com.example.tugaspertemuan9ya.R.array.listticket)
//        val adapterTickets = ArrayAdapter(
//            requireActivity(),
//            android.R.layout.simple_spinner_item, tickets
//        )
//
//        adapterTickets.setDropDownViewResource(
//            com.google.android.material.R.layout.support_simple_spinner_dropdown_item
//        )
//
//        with(binding){
//            spinnerJenisTicket.adapter = adapterTickets;
//            spinnerJenisTicket.onItemSelectedListener=
//                object  : AdapterView.OnItemSelectedListener{
//                    override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id : Long) {
//                        ticketdipilih = tickets[position]
//                    }
//                    override fun onNothingSelected(p0: AdapterView<*>?) {}
//                }
//        }

        binding = FragmentChooseorderticketBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tickets = resources.getStringArray(com.example.tugaspertemuan9ya.R.array.listticket)
        val adapterTickets = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item, tickets
        )

        adapterTickets.setDropDownViewResource(
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item
        )

        with(binding){
            spinnerJenisTicket.adapter = adapterTickets;
            spinnerJenisTicket.onItemSelectedListener=
                object  : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id : Long) {
                        ticketdipilih = tickets[position]
                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {}
                }

            btnBuy2.setOnClickListener(){
                findNavController().apply {
                    previousBackStackEntry?.savedStateHandle?.
                    set("ticketdipilih", ticketdipilih)
                }.navigateUp()
            }



//            lambda expression
            btnBuy2.setOnClickListener(){
            }

//            without lambda expression
            btnBuy2.setOnClickListener( object : View.OnClickListener {
                override fun onClick(v: View?) {

                }
            }




            )





        }
    }


}