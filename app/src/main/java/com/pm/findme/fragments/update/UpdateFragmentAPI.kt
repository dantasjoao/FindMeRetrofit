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
import com.pm.findme.api_FindMe.dto.CompanyDto
import com.pm.findme.api_FindMe.requests.CompanyAPI
import com.pm.findme.api_FindMe.retrofit.ServiceBuilder
import com.pm.findme.model.Company
import com.pm.findme.utils.Utils.Companion.getToken
import com.pm.findme.utils.Utils.Companion.somethingWentWrong
import com.pm.findme.utils.Utils.Companion.unauthorized
import com.pm.findme.viewmodel.CompanyViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateFragmentAPI : Fragment() {

    private val args by navArgs<UpdateFragmentAPIArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)



        view.update_companyName.setText(args.currentCompany.nameCompany)
        view.update_companyEmail.setText(args.currentCompany.emailCompany)
        view.update_companyPhone.setText(args.currentCompany.phoneCompany.toString())
        view.update_companyAddress.setText(args.currentCompany.addressCompany)
        view.update_companyCategory.setText(args.currentCompany.categoryCompany)

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
            updateCompany()
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

    private fun updateCompany() {
        val companyName = update_companyName.text.toString()
        val companyEmail = update_companyEmail.text.toString()
        val companyPhone = Integer.parseInt(update_companyPhone.text.toString())
        val companyAddress = update_companyAddress.text.toString()
        val companyCategory = update_companyCategory.text.toString()
        if (TextUtils.isEmpty(companyName) || TextUtils.isEmpty(
                (companyEmail)
            )
        ) {
            Toast.makeText(
                requireContext(),
                getString(R.string.fill_title_and_description),
                Toast.LENGTH_LONG
            )
                .show()
        } else {
            val request = ServiceBuilder.buildService(CompanyAPI::class.java)
            val call = request.updateReport(
                token = "Bearer ${getToken()}",
                id = args.currentCompany.id,
                name = companyName,
                email = companyEmail,
                phone = companyPhone,
                address = companyAddress,
                category = companyCategory
            )

            call.enqueue(object : Callback<CompanyDto> {
                override fun onResponse(call: Call<CompanyDto>, response: Response<CompanyDto>) {
                    if (response.isSuccessful) {
                        val company: CompanyDto = response.body()!!

                        if (company.status == "OK") {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.successfull_updated_report),
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.action_updateFragment_to_listFragmend2)
                        } else {
                            Toast.makeText(
                                requireContext(), getString(
                                    resources.getIdentifier(
                                        company.message, "string",
                                        context?.packageName
                                    )
                                ), Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        if (response.code() == 401) {
                            unauthorized(navigatonHandlder = {
                                findNavController().navigate(R.id.action_updateFragment_to_userLoginFragment2)
                            })
                        } else {
                            somethingWentWrong()
                        }
                    }
                }

                override fun onFailure(call: Call<CompanyDto>, t: Throwable) {
                    somethingWentWrong()
                }
            })
        }
    }

    private fun deleteProduct() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->

            val request = ServiceBuilder.buildService(CompanyAPI::class.java)
            val call = request.deleteReport(
                token = "Bearer ${getToken()}",
                id = args.currentCompany.id
            )

            call.enqueue(object : Callback<CompanyDto> {
                override fun onResponse(call: Call<CompanyDto>, response: Response<CompanyDto>) {
                    if (response.isSuccessful) {
                        val company: CompanyDto = response.body()!!

                        if (company.status == "OK") {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.successfull_deleted_report),
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.action_updateFragment_to_listFragmend2)
                        } else {
                            Toast.makeText(
                                requireContext(), getString(
                                    resources.getIdentifier(
                                        company.message, "string",
                                        context?.packageName
                                    )
                                ), Toast.LENGTH_LONG
                            ).show()
                        }

                    } else {

                        if (response.code() == 401) {
                            unauthorized(navigatonHandlder = {
                                findNavController().navigate(R.id.action_updateFragment_to_userLoginFragment2)
                            })
                        } else {
                            somethingWentWrong()
                        }
                    }
                }

                override fun onFailure(call: Call<CompanyDto>, t: Throwable) {
                    somethingWentWrong()
                }
            })
        }
         builder.setNegativeButton(getString(R.string.n)){ _, _ -> }
         builder.setTitle("Delete  ${args.currentCompany.nameCompany}?")
        builder.setMessage("Are you sure you want to delete ${args.currentCompany.nameCompany}?")
        builder.create().show()
    }
}
//O GONCALO E UM BURROOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
