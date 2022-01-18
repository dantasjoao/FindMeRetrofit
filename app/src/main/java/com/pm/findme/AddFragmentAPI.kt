package com.pm.findme

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.pm.findme.api_FindMe.dto.CompanyDto
import com.pm.findme.api_FindMe.requests.CompanyAPI
import com.pm.findme.api_FindMe.retrofit.ServiceBuilder
import com.pm.findme.utils.Utils.Companion.getToken
import com.pm.findme.utils.Utils.Companion.getUserIdInSession
import com.pm.findme.utils.Utils.Companion.somethingWentWrong
import com.pm.findme.utils.Utils.Companion.unauthorized
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFragmentAPI : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        //setHasOptionsMenu(true)


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
           // val product = Company(0, name, email, Integer.parseInt(phone.toString()), address, category, )
            // Add Data to Database
             add()
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
        fun add(){
//llProgressBar.bringToFront()
//llProgressBar.visibility = View.VISIBLE
            val name = companyName.text.toString()
            val email = companyEmail.text.toString()
            val phone = companyPhone.text.toString().toInt()
            val address = companyAddress.text.toString()
            val category = companyCategory.text.toString()


            val request = ServiceBuilder.buildService(CompanyAPI::class.java)
            val call = request.createReport(
                token = "Bearer ${getToken()}",
                users_id = getUserIdInSession(),
                name=name,
                email=email,
                phone=phone,
                address=address,
                category=category
            )

            call.enqueue(object : Callback<CompanyDto> {
                override fun onResponse(call: Call<CompanyDto>, response: Response<CompanyDto>) {
                   // llProgressBar.visibility = View.GONE

                    if (response.isSuccessful) {
                        val company: CompanyDto = response.body()!!

                        if (company.status == "OK") {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.successfull_added_new_note),
                                Toast.LENGTH_LONG
                            ).show()
                            findNavController().navigate(R.id.action_addFragment_to_listFragmend)
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
                                findNavController().navigate(R.id.action_addFragment_to_userLoginFragment)
                            })
                        } else {
                            somethingWentWrong()
                        }
                    }
                }

                override fun onFailure(call: Call<CompanyDto>, t: Throwable) {
                    //llProgressBar.visibility = View.GONE
                    somethingWentWrong()
                }
            })
        }}