package com.example.pcexample.view.abstractions.login

/**
 * Интерфейс представления Login
 */
interface ILoginView {
  /**
   * Показать фрагмент входа
   */
  fun showSignInFragment()

  /**
   * Скрыть фрагмент входа
   */
  fun hideSignInFragment()

  /**
   * Показать фрагмент регистрации
   */
  fun showSignUpFragment()

  /**
   * Скрыть фрагмент регистрации
   */
  fun hideSignUpFragment()

  /**
   * Установить текст кнопки вход/регистрация
   */
  fun setSwitchText(valueResourceId: Int)

  /**
   * Запросить разрешения приложения
   */
  fun requestPermissions(permissions: Array<String>)
}