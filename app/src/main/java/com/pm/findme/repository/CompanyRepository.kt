package com.pm.findme.repository


import androidx.lifecycle.LiveData
import com.pm.findme.data.dao.CompanyDao
import com.pm.findme.model.Company

class CompanyRepository(private  val companyDao: CompanyDao) {
    val readAllProducts : LiveData<List<Company>> = companyDao.readAllProducts()

    suspend fun addProduct(product: Company){
        companyDao.addProduct(product)
    }

    suspend fun updateProduct(product: Company) {
        companyDao.updateProduct(product)
    }

    suspend fun deleteProduct(product: Company) {
        companyDao.deleteProduct(product)
    }
}