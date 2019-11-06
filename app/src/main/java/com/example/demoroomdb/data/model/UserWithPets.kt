package com.example.demoroomdb.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithPets(
    @Embedded val user: User,
    @Relation(parentColumn = "id",
              entity = Pet::class,
              entityColumn = "user_id") val pets: List<Pet>
)