package com.example.demoroomdb.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["user_id"]
    )]
)
data class Pet(
    @PrimaryKey
    val petId: Int = 0,
    var name: String? = "",
    @ColumnInfo(name = "user_id")
    var userId: Int? = 0
) {
}