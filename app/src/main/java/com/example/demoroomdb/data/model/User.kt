package com.example.demoroomdb.data.model

import androidx.room.*

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String? = "",

    @ColumnInfo(name = "phoneNumber")
    var phone: String? = "",

    @Embedded
    var bankAccount: BankAccount? = null,

    @Ignore
    var address: String = ""
) {
}