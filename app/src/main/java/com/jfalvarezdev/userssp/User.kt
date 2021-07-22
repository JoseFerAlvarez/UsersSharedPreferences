package com.jfalvarezdev.userssp

data class User(val id: Long, var name: String, var lastName: String, var url: String) {

    fun getFullName(): String{
        return "$name $lastName"
    }
}