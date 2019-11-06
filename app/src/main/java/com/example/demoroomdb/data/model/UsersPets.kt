package com.example.demoroomdb.data.model

import androidx.room.Entity

@Entity(primaryKeys = ["id", "user_id"])
data class UsersPets(val id: Int, val userId: Int)