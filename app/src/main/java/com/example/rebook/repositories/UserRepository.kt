package com.example.rebook.repositories

import androidx.lifecycle.LiveData
import com.example.rebook.helper.DatabaseHelper
import com.example.rebook.model.Users

class UserRepository(private val helper: DatabaseHelper) {
    fun updateUserData(name:String, email:String, gender:String, birthday:String, password:String):Int{
        return helper.updateUserData(name,email,gender,birthday, password)
    }
    fun insertUser(name:String, email:String, gender:String, birthday:String, password:String):Long{
        return helper.insertUser(name,email,gender,birthday, password)
    }

    fun deleteUser(idUser: Int):Int{
        return helper.deleteUser(idUser)
    }
}