package com.example.pcexample.presentation.login

import android.Manifest
import com.example.pcexample.R
import com.example.pcexample.model.abstractions.ILoginService
import com.example.pcexample.presentation.abstractions.IPresenter
import com.example.pcexample.view.abstractions.login.ILoginView
import com.example.pcexample.view.login.LoginActivity

/**
 * Представитель формы входа/регистрации
 */
class LoginPresenter : IPresenter<ILoginView> {
  /**
   * @inheritDoc
   */
  override fun onViewCreated(view: ILoginView) {
    this.view = view
    setupFragments()
    view.requestPermissions(NEEDED_PERMISSIONS)
  }

  /**
   * @inheritDoc
   */
  override fun onDestroy() {
    view = null
  }

  /**
   * Колбэк при вызове сохранения текущего состояния фрагментов
   */
  fun onSaveCurrentSwitchState(): Int = currentSwitchButtonState

  /**
   * Колбэк при вызове восстановления текущего состояния фрагментов
   */
  fun onRestoreCurrentSwitchState(currentSwitchState: Int) {
    if (currentSwitchButtonState != currentSwitchState) {
      currentSwitchButtonState = currentSwitchState
      setupFragments()
    }
  }

  fun onPermissionsGranted() {
    isPermissionsGranted = true
    setupFragments()
  }

  fun onPermissionsGrantFail() {
    isPermissionsGranted = false
    setupFragments()
  }

  /**
   * Колбэк при нажатии на кнопку переключения фрагментов вход/регистрация
   */
  fun onSwitchClick() {
    //Инвертируем текущее состояние
    currentSwitchButtonState = if (currentSwitchButtonState == SIGN_IN_STATE) {
      SIGN_UP_STATE
    } else {
      SIGN_IN_STATE
    }
    //выполняем настройку фрагментов
    setupFragments()
  }

  /**
   * Установить фрагменты
   */
  private fun setupFragments() {
    if(!isPermissionsGranted) {
      //скрываем фрагмент входа
      view?.hideSignInFragment()
      //скрываем фрагмент регистрации
      view?.hideSignUpFragment()
      return
    }

    //Проверяем текущее состояние кнопки
    if (currentSwitchButtonState == SIGN_IN_STATE) {
      //если должен быть показан фрагмент входа
      //показываем фрагмент входа
      view?.showSignInFragment()
      //скрываем фрагмент регистрации
      view?.hideSignUpFragment()
      //Меняем текст кнопки на "регистрация"
      view?.setSwitchText(R.string.registration)

    } else {
      //если должен быть показан фрагмент регистрации
      //показываем фрагмент регистрации
      view?.showSignUpFragment()
      //скрываем фрагмент входа
      view?.hideSignInFragment()
      //Меняем текст кнопки на "вход"
      view?.setSwitchText(R.string.sign_in)
    }
  }

  private var view: ILoginView? = null

  //Переменная, указывающая какой фрагмент в текущий момент показан на экране
  private var currentSwitchButtonState = SIGN_IN_STATE
  private var isPermissionsGranted = false

  companion object {
    private const val SIGN_IN_STATE = 0 //Фрагмент вход
    private const val SIGN_UP_STATE = 1 //фрагмент регистрация

    private val NEEDED_PERMISSIONS = arrayOf(
      Manifest.permission.CAMERA
    )
  }
}