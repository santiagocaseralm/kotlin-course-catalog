package com.kotlinspring.entity

object Authenticate {
    @JvmStatic
    fun authenticate(userName: String, password: String){
        println("user name is $userName, password is $password")
    }
}