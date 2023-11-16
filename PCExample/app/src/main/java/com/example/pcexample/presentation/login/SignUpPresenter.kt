package com.example.pcexample.presentation.login

import com.example.pcexample.R
import com.example.pcexample.di.App
import com.example.pcexample.presentation.abstractions.IPresenter
import com.example.pcexample.view.abstractions.login.ISignUpView

class SignUpPresenter : IPresenter<ISignUpView> {
  override fun onViewCreated(view: ISignUpView) {
    this.view = view
  }

  override fun onDestroy() {
    view = null
  }

  /**
   * Колбэк при изменении текста в поле "Логин"
   */
  fun onLoginTextChanged(login: String) {
    this.login = login
  }

  /**
   * Колбэк при изменении текста в поле "Пароль"
   */
  fun onPasswordChanged(password: String) {
    this.password = password
  }

  /**
   * Колбэк при изменении текста в поле "Email"
   */
  fun onEmailChanged(email: String) {
    this.email = email
  }

  /**
   * Колбэк при изменении текста в поле "Номер телефона"
   */
  fun onPhoneChanged(phone: String) {
    this.phone = phone
  }

  /**
   * Колбэк при изменении нажатии на кнопку "Регистрация"
   */
  suspend fun onRegisterButtonClick() {
    var success = true
    if (login.isBlank()){
      success = false
      view?.setLoginBackground(R.drawable.ic_warning_frame)
    } else {
      view?.setLoginBackground(R.drawable.ic_edit_text_background)
    }

    if (password.isBlank()){
      success = false
      view?.setPasswordBackground(R.drawable.ic_warning_frame)
    } else {
      view?.setPasswordBackground(R.drawable.ic_edit_text_background)
    }

    if (email.isBlank()){
      success = false
      view?.setEmailBackground(R.drawable.ic_warning_frame)
    } else {
      view?.setEmailBackground(R.drawable.ic_edit_text_background)
    }

    if (phone.isBlank()){
      success = false
      view?.setPhoneBackground(R.drawable.ic_warning_frame)
    } else {
      view?.setPhoneBackground(R.drawable.ic_edit_text_background)
    }

    if(success) {
      App.loginService.signUp(login, password, email, phone)
      view?.startMainActivity()
    }
  }

  private var login: String = ""
  private var password: String = ""
  private var email: String = ""
  private var phone: String = ""

  private var view : ISignUpView? = null
}