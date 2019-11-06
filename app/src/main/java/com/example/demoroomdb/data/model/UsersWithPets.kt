package com.example.demoroomdb.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class UsersWithPets(
    @Embedded val user: User,
    @Relation(parentColumn = "id",
        entity = Pet::class,
        entityColumn = "user_id",
        associateBy = Junction(UsersPets::class)
    ) val pets: List<Pet>
)