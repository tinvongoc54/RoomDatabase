package com.example.demoroomdb.data

import com.example.demoroomdb.data.model.User
import io.reactivex.Flowable

interface UserDataSource {
    fun getUserByUserId(userId: Int): Flowable<User>

    fun getUserByName(userName: String): Flowable<List<User>>

    fun getALlUser(): Flowable<List<User>>

    fun insertUser(users: User)

    fun deleteUser(user: User)

    fun deleteAllUser()

    fun updateUser(users: User)
}