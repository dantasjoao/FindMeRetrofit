package com.pm.findme.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.pm.findme.model.Company

@Dao
interface CompanyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addProduct(product: Company)

    @Update
    fun updateProduct(product: Company)

    @Query("SELECT * FROM Company ORDER BY id DESC")
    fun readAllProducts(): LiveData<List<Company>>

    @Delete
    fun deleteProduct(product: Company)


}