package com.hmn.ui.room

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(entity: List<CategiryEntity>)

    @Update
    fun updateCategory(entity: CategiryEntity)

    @Delete
    fun deleteCategory(entity: CategiryEntity)

    @Query("Delete From category_table")
    fun deleteAllCategory()

    @Query("SELECT * From category_table")
    fun getAllCategory(): List<CategiryEntity>


    @Query("SELECT * FROM category_table WHERE isAlarm =:isAlarm")
    fun getAlarm(isAlarm: Boolean):List<CategiryEntity>


    @Query("DELETE FROM category_table WHERE id = :id")
    fun deleteById(id: Int)

}