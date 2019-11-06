package com.example.demoroomdb.data.local

import androidx.room.*
import androidx.room.Dao
import com.example.demoroomdb.data.model.User
import com.example.demoroomdb.data.model.UserAndPet
import com.example.demoroomdb.data.model.UserWithPets
import com.example.demoroomdb.data.model.UsersWithPets
import io.reactivex.Flowable

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getALllUser(): Flowable<List<User>>

    @Query("SELECT * FROM User WHERE id = :userId")
    fun getUserByUserId(userId: Int): Flowable<User>

    @Query("SELECT * FROM User WHERE name LIKE :userName")
    fun getUserByName(userName: String): Flowable<List<User>>

    @Transaction
    @Query("SELECT * FROM User")
    fun getUserAndPet(): List<UserAndPet>

    @Transaction
    @Query("SELECT * FROM User")
    fun getUserWithPets(): List<UserWithPets>

    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersWithPets(): List<UsersWithPets>

    @Insert
    fun insertUser(users: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM User")
    fun deleteAllUser()

    @Update
    fun updateUser(users: User)
}