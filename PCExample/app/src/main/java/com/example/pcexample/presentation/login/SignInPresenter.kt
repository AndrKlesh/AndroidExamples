package com.example.pcexample.presentation.login

import com.example.pcexample.R
import com.example.pcexample.di.App
import com.example.pcexample.presentation.abstractions.IPresenter
import com.example.pcexample.view.abstractions.login.ISignInView

/**
 * Представитель формы входа
 */
class SignInPresenter : IPresenter<ISignInView> {

  override fun onViewCreated(view: ISignInView) {
    this.view = view
    view.setProgressBarVisibility(false)
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
   * Колбэк при нажатии на кнопку "Вход"
   */
  suspend fun onEnterButtonPressed() {
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

    if(success){
      view?.setProgressBarVisibility(true)
      try {
        if(App.loginService.signIn(login, password)) {
          view?.startMainActivity(0)
        } else {
          view?.startMainActivity(1)
        }
      } finally {
        view?.setProgressBarVisibility(false)
      }
    }
  }



  private var login: String = ""
  private var password: String = ""

  private var view: ISignInView? = null
}