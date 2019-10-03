package com.example.demoroomdb.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demoroomdb.data.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var sUserDatabase: UserDatabase? = null

        val DATABASE_NAME = "Room-database"

        fun getInstance(context: Context): UserDatabase? {
            if (sUserDatabase == null) {
                sUserDatabase =
                    Room.databaseBuilder(context, UserDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return sUserDatabase
        }
    }
}