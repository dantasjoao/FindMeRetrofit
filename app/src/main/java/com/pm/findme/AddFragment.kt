package com.pm.findme

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pm.findme.model.Company
import com.pm.findme.viewmodel.CompanyViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: CompanyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        //setHasOptionsMenu(true)

        mUserViewModel = ViewModelProvider( this).get(CompanyViewModel::class.java)

        view.add_btn.setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val name = companyName.text.toString()
        val email = companyEmail.text.toString()
        val phone = companyPhone.text
        val address = companyAddress.text.toString()
        val category = companyCategory.text.toString()

        if(inputCheck(name, email, phone, address, category)){
            // Create Company object
            val product = Company(0, name, email, Integer.parseInt(phone.toString()), address, category, )
            // Add Data to Database
            mUserViewModel.addProduct(product)
            Toast.makeText(requireContext(), getString(R.string.added), Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragmend)
        }else{
            Toast.makeText(requireContext(),getString(R.string.fields_1), Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String, email: String, phone: Editable, address: String, category: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && phone.isEmpty() && TextUtils.isEmpty(address) && TextUtils.isEmpty(category))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_company, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //hideKeyboard()

        if (item.itemId == R.id.menu_add) {
            //addProduct()
        }

        return super.onOptionsItemSelected(item)
    }
}