package com.example.pcexample.model.abstractions

interface ILoginService {
  suspend fun signIn(
    username: String,
    password: String,
  ): Boolean

  suspend fun signOut()

  suspend fun signUp(
    username: String,
    password: String,
    email: String,
    phoneNumber: String
  ) : Boolean

  var username: String
  var token: Int
}