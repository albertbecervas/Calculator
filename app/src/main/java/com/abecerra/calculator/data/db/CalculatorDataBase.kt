package com.abecerra.calculator.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.abecerra.calculator.core.utils.extensions.context
import com.abecerra.calculator.data.db.dao.ComputedCalculationsDao
import com.abecerra.calculator.data.db.entities.ComputedCalculationEntity

@Database(entities = [ComputedCalculationEntity::class], version = 1)
abstract class CalculatorDataBase : RoomDatabase() {

    abstract fun getComputedCalculationsDao(): ComputedCalculationsDao

    companion object {

        private const val DATABASE_NAME = "calculator_database"

        fun build(): CalculatorDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                CalculatorDataBase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}