package com.pm.findme.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pm.findme.ListFragmendAPIDirections

import com.pm.findme.R
import com.pm.findme.api_FindMe.models.Company
import kotlinx.android.synthetic.main.custom_row.view.*
import java.util.Collections.emptyList

class ListAdapterAPI(userIdInSession: String?): RecyclerView.Adapter<ListAdapterAPI.MyViewHolder>(){

    private var companyList = emptyList<Company>()
    private  val _userIdInSession = userIdInSession
    private  var  _ctx : Context? = null
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        _ctx = parent.context
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = companyList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.companyName_txt.text = currentItem.nameCompany
        holder.itemView.companyEmail_txt.text = currentItem.emailCompany
        holder.itemView.companyPhone_txt.text = currentItem.phoneCompany.toString()
        holder.itemView.companyAddress_txt.text = currentItem.addressCompany
        holder.itemView.companyCategory_txt.text = currentItem.categoryCompany

        holder.itemView.rowLayout.setOnClickListener{
            val action = ListFragmendAPIDirections.actionListFragmendToUpdateFragment2(currentItem)
            holder.itemView.findNavController().navigate(action)
        }


    }

    override fun getItemCount(): Int {
        return companyList.size
    }

    fun setData(company: List<Company>){
        this.companyList = company
        notifyDataSetChanged()
    }
}