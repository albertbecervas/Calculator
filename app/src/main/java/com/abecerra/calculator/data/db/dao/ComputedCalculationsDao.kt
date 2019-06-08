package com.abecerra.calculator.data.db.dao

import android.arch.persistence.room.*
import com.abecerra.calculator.data.db.entities.ComputedCalculationEntity

@Dao
interface ComputedCalculationsDao {

    @Insert
    fun insert(calculation: ComputedCalculationEntity)

    @Update
    fun update(vararg calculation: ComputedCalculationEntity)

    @Delete
    fun delete(vararg calculation: ComputedCalculationEntity)

    @Query("SELECT * FROM " + ComputedCalculationEntity.TABLE_NAME + " ORDER BY time")
    fun getCalculations(): List<ComputedCalculationEntity>

}