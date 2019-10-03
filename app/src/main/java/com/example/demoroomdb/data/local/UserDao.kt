package com.example.demoroomdb.data.local

import androidx.room.*
import androidx.room.Dao
import com.example.demoroomdb.data.model.User
import io.reactivex.Flowable

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getALllUser(): Flowable<List<User>>

    @Query("SELECT * FROM User WHERE id = :userId")
    fun getUserByUserId(userId: Int): Flowable<User>

    @Query("SELECT * FROM User WHERE name LIKE :userName")
    fun getUserByName(userName: String): Flowable<List<User>>

    @Insert
    fun insertUser(users: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM User")
    fun deleteAllUser()

    @Update
    fun updateUser(users: User)
}