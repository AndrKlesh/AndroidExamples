package com.example.pcexample.model

import com.example.pcexample.model.abstractions.ILoginService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random
import kotlin.random.nextInt

class LoginService : ILoginService {
  override suspend fun signIn (
    username: String,
    password: String
  ) : Boolean {
    return if(username == "" || password == "" ) {
      false
    } else {
      withContext(Dispatchers.IO) {
        delay(delayInterval)
      }
      if(username == "123"){
        return false
      }
      token = username.length + password.length
      true
    }
  }

  override suspend fun signOut() {
    token = 0
  }

  override suspend fun signUp(
    username: String,
    password: String,
    email: String,
    phoneNumber: String
  ) : Boolean {
    withContext(Dispatchers.IO) {
      delay(delayInterval)
    }
    token = username.length + password.length
    return true
  }

  override var username: String = ""
  override var token: Int = 0
  private val delayInterval = Random.nextInt(1000..2500).toLong()
}