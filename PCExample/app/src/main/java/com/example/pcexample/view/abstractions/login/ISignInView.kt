package com.example.pcexample.view.abstractions.login

interface ISignInView {
  /**
   * Установить фон поля "Логин"
   * @param resourceId ID ресурса
   */
  fun setLoginBackground(resourceId: Int)
  /**
   * Установить фон поля "Пароль"
   * @param resourceId ID ресурса
   */
  fun setPasswordBackground(resourceId: Int)

  /**
   * Установить видимость progress bar
   * true -> visible
   * false -> invisible
   */
  fun setProgressBarVisibility(state: Boolean)

  /**
   * Запустить главное окно
   */
  fun startMainActivity(parameter: Int)
}