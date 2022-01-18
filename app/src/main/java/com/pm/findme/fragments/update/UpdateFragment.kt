package com.pm.findme.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pm.findme.R
import com.pm.findme.model.Company
import com.pm.findme.viewmodel.CompanyViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    /*private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: CompanyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(CompanyViewModel::class.java)

        view.update_companyName.setText(args.currentCompany.companyName)
        view.update_companyEmail.setText(args.currentCompany.companyEmail)
        view.update_companyPhone.setText(args.currentCompany.companyPhone.toString())
        view.update_companyAddress.setText(args.currentCompany.companyAddress)
        view.update_companyCategory.setText(args.currentCompany.companyCategory)

        view.update_add_btn.setOnClickListener {
            updateItem()
        }
        // Add menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val companyName = update_companyName.text.toString()
        val companyEmail = update_companyEmail.text.toString()
        val companyPhone = Integer.parseInt(update_companyPhone.text.toString())
        val companyAddress = update_companyAddress.text.toString()
        val companyCategory = update_companyCategory.text.toString()


        if(inputCheck(companyName, companyEmail, update_companyPhone.text, companyAddress, companyCategory)){
            //Create Company Object
            val updateUser = Company(args.currentCompany.id, companyName, companyEmail, companyPhone, companyAddress, companyCategory)
            // Update Current Company
            mUserViewModel.updateProduct(updateUser)
            Toast.makeText(requireContext(),getString(R.string.updated), Toast.LENGTH_SHORT).show()
            //Navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragmend)
        }else{
            Toast.makeText(requireContext(), getString(R.string.fields), Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(name: String, email: String, phone: Editable, address: String, category: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && phone.isEmpty() && TextUtils.isEmpty(address) && TextUtils.isEmpty(category))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete_company, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete_company){
            deleteProduct()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteProduct(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.y)) { _, _ ->
            mUserViewModel.deleteProduct(args.currentCompany)
            Toast.makeText(
                requireContext(),
                 "Successfully removed: ${args.currentCompany.companyName}",
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton(getString(R.string.n)){ _, _ -> }
        builder.setTitle("Delete  ${args.currentCompany.companyName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentCompany.companyName}?")
        builder.create().show()
    }

     */
}

