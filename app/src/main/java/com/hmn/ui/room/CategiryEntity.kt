package com.hmn.ui.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class CategiryEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String= "",

    @ColumnInfo(name = "time")
    var time: String = "",

    @ColumnInfo(name = "isAlarm")
    var isAlarm: Boolean = false,




    )