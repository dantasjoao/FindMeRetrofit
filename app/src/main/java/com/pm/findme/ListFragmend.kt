package com.pm.findme

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pm.findme.viewmodel.CompanyViewModel
import kotlinx.android.synthetic.main.fragment_list_fragmend.view.*


class ListFragmend : Fragment() {
/*
    private lateinit var mUserViewModel: CompanyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_fragmend, container, false)

        //Recyclerview
        val adapter = com.pm.findme.fragments.ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel
        mUserViewModel = ViewModelProvider(this).get(CompanyViewModel::class.java)
        mUserViewModel.readAllProducts.observe(viewLifecycleOwner, Observer { company ->
            adapter.setData(company)
        })


        view.btnAddProductFromList.setOnClickListener{
            findNavController().navigate(R.id.action_listFragmend_to_addFragment)
        }

        //Add
        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete_company, menu)
    }

*/
}