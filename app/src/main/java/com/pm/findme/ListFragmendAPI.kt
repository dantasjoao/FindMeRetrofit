package com.pm.findme

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pm.findme.api_FindMe.models.Company
import com.pm.findme.api_FindMe.requests.CompanyAPI
import com.pm.findme.api_FindMe.retrofit.ServiceBuilder
import com.pm.findme.fragments.ListAdapterAPI
import com.pm.findme.utils.Utils.Companion.getToken
import com.pm.findme.utils.Utils.Companion.getUserIdInSession
import com.pm.findme.utils.Utils.Companion.somethingWentWrong
import com.pm.findme.utils.Utils.Companion.unauthorized
import com.pm.findme.viewmodel.CompanyViewModel
import kotlinx.android.synthetic.main.fragment_list_fragmend.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragmendAPI : Fragment() {
    private  var  _view : View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_fragmend, container, false)

        view.btnAddProductFromList.setOnClickListener{
            findNavController().navigate(R.id.action_listFragmend_to_addFragment)
        }

        _view = view

        setHasOptionsMenu(true)

        getAndSetData(view)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete_company, menu)
    }
    private fun getAndSetData(view: View) {

       // view.llProgressBarList.bringToFront()
        // view.llProgressBarList.visibility = View.VISIBLE


        val adapter = ListAdapterAPI(getUserIdInSession())

        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val request = ServiceBuilder.buildService(CompanyAPI::class.java)
        val call = request.getReports(token = "Bearer ${getToken()}")

        call.enqueue(object : Callback<List<Company>> {
            override fun onResponse(call: Call<List<Company>>, response: Response<List<Company>>) {

               //llProgressBarList.visibility = View.GONE

                if (response.isSuccessful) {
                    val companies: List<Company> = response.body()!!
                    adapter.setData(companies)
                } else {
                    if (response.code() == 401) {
                        unauthorized(navigatonHandlder = {
                            findNavController().navigate(R.id.action_listFragmend_to_userLoginFragment)
                        })
                    } else {
                        somethingWentWrong()
                    }
                }
            }

            override fun onFailure(call: Call<List<Company>>, t: Throwable) {
               // llProgressBarList.visibility = View.GONE
                somethingWentWrong()
            }
        })
    }
    private fun logout() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
            val preferences = requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
            preferences.edit().putString("token", null).apply()
            findNavController().navigate(R.id.action_listFragmend_to_userLoginFragment)
        }
        builder.setNegativeButton(getString(R.string.no)) { _, _ -> }
        builder.setTitle(getString(R.string.logout))
        builder.setMessage(getString((R.string.logout_question)))
        builder.create().show()
    }
}