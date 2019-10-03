package com.example.demoroomdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BankAccount")
data class BankAccount(
    @PrimaryKey
    @ColumnInfo(name = "nameAccount")
    var nameAccount: String? = "",
    @ColumnInfo(name = "numberAccount")
    var numberAccount: String? = ""
) {
}