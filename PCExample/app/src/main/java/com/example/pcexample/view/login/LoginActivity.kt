package com.example.pcexample.view.login

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.pcexample.R
import com.example.pcexample.di.App
import com.example.pcexample.presentation.login.LoginPresenter
import com.example.pcexample.view.abstractions.login.ILoginView
import com.example.pcexample.view.main.MainActivity

/**
 * Activity для экрана Вход/Регистрация
 */
class LoginActivity :
  AppCompatActivity(),
  //Реализуем интерфейс OnClickListener, для "прослушивания" колбэков нажатий на элементы управления
  View.OnClickListener,
  ILoginView {

  /**
   * Колбэк, вызываемый при создании Activity
   * Здесь проводим инициализацию необзодимых элементов
   */
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //Устанавливаем разметку UI
    setContentView(R.layout.activity_login)
    //Выполняем поиск кнопки в разметке
    loginRegistrationSwitchButton = findViewById(R.id.login_registration_switch_button)
    //Устанавливаем Activity слушателем колбэков OnClick для кнопки-переключателя Вход/Регистрация
    loginRegistrationSwitchButton.setOnClickListener(this)
    //Выполняем поиск фрагментов входа и регистрации
    signInFragment = supportFragmentManager.findFragmentById(R.id.sign_in_fragment)!!
    signUpFragment = supportFragmentManager.findFragmentById(R.id.sign_up_fragment)!!
    //восстанавливаем состояние activity (переменную указывающую какой фрагмент в текущий момент показан на экране)
    presenter.onViewCreated(this)
    val currentSwitchButtonState =
      savedInstanceState?.getInt(CURRENT_SWITCH_BUTTON_STATE_PARAM_NAME) ?: 0
    presenter.onRestoreCurrentSwitchState(currentSwitchButtonState)
  }

  /**
   * Колбэк для сохранения текущего состояния
   */
  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    //Сохраняем текущее состояние фрагментов в bundle
    val currentSwitchButtonState = presenter.onSaveCurrentSwitchState()
    outState.putInt(CURRENT_SWITCH_BUTTON_STATE_PARAM_NAME, currentSwitchButtonState)
  }

  /**
   * Колбэк, вызываемый при уничтожении Activity
   * Здесь освобождаем ресурсы
   */
  override fun onDestroy() {
    super.onDestroy()
    //Отписываемся от колбэков, для корректной работы сборщика мусора
    loginRegistrationSwitchButton.setOnClickListener(null)
    presenter.onDestroy()
  }

  /**
   * @inheritDoc
   */
  override fun showSignInFragment() {
    //показываем фрагмент входа
    setVisibilityFragment(signInFragment, true)
  }

  /**
   * @inheritDoc
   */
  override fun hideSignInFragment() {
    //Скрываем фрагмент входа
    setVisibilityFragment(signInFragment, false)
  }

  /**
   * @inheritDoc
   */
  override fun showSignUpFragment() {
    //показываем фрагмент регистрации
    setVisibilityFragment(signUpFragment, true)
  }

  /**
   * @inheritDoc
   */
  override fun hideSignUpFragment() {
    //скрываем фрагмент регистрации
    setVisibilityFragment(signUpFragment, false)
  }

  /**
   * @inheritDoc
   */
  override fun setSwitchText(valueResourceId: Int) {
    loginRegistrationSwitchButton.setText(valueResourceId)
  }

  /**
   * Колбэк, вызываемый при нажатии на элемент управления.
   * В текущей ситуации - кнопки Вход/Регистрация
   */
  override fun onClick(view: View?) {
    //Проверяем, что элемент на который нажали - это наша кнопка
    if (view == loginRegistrationSwitchButton) {
      presenter.onSwitchClick()
    }
  }

  override fun requestPermissions(permissions: Array<String>) {
    if (permissions.all {
        ContextCompat.checkSelfPermission(
          this,
          it
        ) == PackageManager.PERMISSION_GRANTED
      }) {
      presenter.onPermissionsGranted()
    } else {
      requestPermissions(permissions, PERMISSION_REQUEST_CODE)
    }
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    when (requestCode) {
      PERMISSION_REQUEST_CODE -> {
        if (grantResults.all { gr -> gr == PackageManager.PERMISSION_GRANTED }) {
          presenter.onPermissionsGranted()
        } else {
          presenter.onPermissionsGrantFail()
        }
      }
    }
  }

  /**
   * Функция показывающая/скрывающая фрагменты
   */
  private fun setVisibilityFragment(fragment: Fragment?, isVisible: Boolean) {
    //Проверяем фрагмент на null
    if (fragment === null) {
      return
    }
    //Начинаем транзакцию
    val t = supportFragmentManager.beginTransaction()
    t.setReorderingAllowed(true)
    //В зависимости от параметра
    if (isVisible) {
      //Показываем
      t.show(fragment)
    } else {
      //Скрываем
      t.hide(fragment)
    }
    //Коммитим транзакцию
    t.commit()
  }

  private lateinit var signInFragment: Fragment
  private lateinit var signUpFragment: Fragment
  private lateinit var loginRegistrationSwitchButton: AppCompatButton
  private val presenter = LoginPresenter()


  companion object {
    //имя параметра для сохранения в bundle текущего состояния фрагментов
    private const val CURRENT_SWITCH_BUTTON_STATE_PARAM_NAME =
      "CURRENT_SWITCH_BUTTON_STATE_PARAM_NAME"
    private const val PERMISSION_REQUEST_CODE = 0
  }
}