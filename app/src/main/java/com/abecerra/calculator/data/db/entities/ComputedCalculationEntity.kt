package com.abecerra.calculator.data.db.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = ComputedCalculationEntity.TABLE_NAME)
data class ComputedCalculationEntity(
    @ColumnInfo(name = "result")
    var result: Float,
    @ColumnInfo(name = "operation")
    var operation: String
) {

    companion object {
        const val TABLE_NAME = "ComputedCalculations"
    }

    @PrimaryKey
    @ColumnInfo(name = "time")
    var time: Long = 0L

}