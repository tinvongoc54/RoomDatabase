package com.example.demoroomdb.data

import com.example.demoroomdb.data.model.User
import io.reactivex.Flowable

class UserRepository(private val mLocalDataSource: UserDataSource) : UserDataSource {
    override fun getALlUser(): Flowable<List<User>> {
        return mLocalDataSource.getALlUser()
    }

    override fun getUserByUserId(userId: Int): Flowable<User> {
        return mLocalDataSource.getUserByUserId(userId)
    }

    override fun getUserByName(userName: String): Flowable<List<User>> {
        return mLocalDataSource.getUserByName(userName)
    }

    override fun insertUser(users: User) {
        mLocalDataSource.insertUser(users)
    }

    override fun deleteUser(user: User) {
        mLocalDataSource.deleteUser(user)
    }

    override fun deleteAllUser() {
        mLocalDataSource.deleteAllUser()
    }

    override fun updateUser(users: User) {
        mLocalDataSource.updateUser(users)
    }

    companion object {
        private var sInstance: UserRepository? = null

        fun getInstance(localDataSource: UserDataSource): UserRepository {
            if (sInstance == null) {
                sInstance = UserRepository(localDataSource)
            }
            return sInstance as UserRepository
        }
    }
}
