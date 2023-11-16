package com.example.pcexample.di

import android.app.Application
import com.example.pcexample.model.LoginService
import com.example.pcexample.model.MetersService
import com.example.pcexample.model.abstractions.ILoginService
import com.example.pcexample.model.abstractions.meters.IMetersService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class App : Application() {
  override fun onCreate() {
    super.onCreate()
    loginService = LoginService()
    metersService = MetersService()
  }

  companion object {
    lateinit var loginService: ILoginService
    lateinit var metersService: IMetersService
  }
}