package com.example.demoroomdb.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserAndPet(
    @Embedded val user: User,
    @Relation(parentColumn = "id", entityColumn = "user_id") val pet: Pet
)