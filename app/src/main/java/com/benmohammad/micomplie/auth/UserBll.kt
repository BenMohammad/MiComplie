package com.benmohammad.micomplie.auth

import androidx.lifecycle.LiveData
import com.benmohammad.micomplie.data.runOnIoThread

class UserBll(
    private val dao: UserDao
) {
    fun signUp(user: String, pass: String) {
        runOnIoThread{
            dao.insertUser(User(user, pass))
        }
    }

    fun login(user: String, pass: String): LiveData<List<User>> {
        return dao.login(user, pass)
    }

    fun logout(username: String) {
        runOnIoThread{
            dao.deleteUser(username)
        }
    }

    fun updateUser(user: String, pass: String) {
        runOnIoThread{
            dao.updateUser(User(user, pass))
        }

    }}