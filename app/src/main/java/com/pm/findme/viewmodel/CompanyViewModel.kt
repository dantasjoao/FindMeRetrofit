package com.pm.findme.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pm.findme.data.database.CompanyDatabase
import com.pm.findme.model.Company
import com.pm.findme.repository.CompanyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CompanyViewModel(application: Application) : AndroidViewModel(application){
    val readAllProducts: LiveData<List<Company>>
    private val repository: CompanyRepository

    init {
        val companyDao = CompanyDatabase.getDatabase(application).companyDao()
        repository = CompanyRepository(companyDao)
        readAllProducts = repository.readAllProducts
    }

    fun  addProduct(product: Company){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(product)
        }
    }

    fun updateProduct(product: Company) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateProduct(product)
        }
    }

    fun  deleteProduct(product: Company) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProduct(product)
        }
    }


}