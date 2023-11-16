package com.example.pcexample.view.abstractions.login

interface ISignUpView {
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
   * Установить фон поля "Email"
   * @param resourceId ID ресурса
   */
  fun setEmailBackground(resourceId: Int)

  /**
   * Установить фон поля "Номер телефона"
   * @param resourceId ID ресурса
   */
  fun setPhoneBackground(resourceId: Int)

  /**
   * Запустить главное окно
   */
  fun startMainActivity()
}