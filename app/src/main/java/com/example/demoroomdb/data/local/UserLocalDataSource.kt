package com.example.demoroomdb.data.local

import com.example.demoroomdb.data.UserDataSource
import com.example.demoroomdb.data.model.User
import io.reactivex.Flowable

class UserLocalDataSource(private val userDao: UserDao) : UserDataSource {
    override fun getALlUser(): Flowable<List<User>> {
        return userDao.getALllUser()
    }

    override fun getUserByUserId(userId: Int): Flowable<User> {
        return userDao.getUserByUserId(userId)
    }

    override fun getUserByName(userName: String): Flowable<List<User>> {
        return userDao.getUserByName(userName)
    }

    override fun insertUser(users: User) {
        userDao.insertUser(users)
    }

    override fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    override fun deleteAllUser() {
        userDao.deleteAllUser()
    }

    override fun updateUser(users: User) {
        userDao.updateUser(users)
    }

    companion object {
        private var sInstance: UserLocalDataSource? = null

        fun getInstance(userDAO: UserDao): UserLocalDataSource {
            if (sInstance == null) {
                sInstance = UserLocalDataSource(userDAO)
            }
            return sInstance as UserLocalDataSource
        }
    }
}