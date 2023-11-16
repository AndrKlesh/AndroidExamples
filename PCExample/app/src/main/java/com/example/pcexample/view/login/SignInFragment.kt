package com.example.pcexample.view.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.pcexample.R
import com.example.pcexample.presentation.login.SignInPresenter
import com.example.pcexample.view.abstractions.login.ISignInView
import com.example.pcexample.view.main.MainActivity
import kotlinx.coroutines.launch

/**
 * [Fragment] Входа в систему
 * Используйте [SignInFragment.newInstance] для создания фрагмента
 */
class SignInFragment :
  Fragment(),
  OnClickListener,
  ISignInView {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_sign_in, container, false)
  }

  /**
   * Колбэк, вызываемый при создании View
   * В нем инициализируем переменные
   */
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    //Ищем в разметке кнопку вход
    signInButton = view.findViewById(R.id.sign_in_button)
    //Устанавливаем текущий фрагмент слушателем колбэка OnClick кнопки вход
    signInButton.setOnClickListener(this)

    //Ищем поля ввода имени пользователя и пароля
    loginEditText = view.findViewById(R.id.sign_in_login_edit_text)
    passwordEditText = view.findViewById(R.id.sign_in_password_edit_text)
    //Ищем progress bar
    signInProgressBar = view.findViewById(R.id.sign_in_progress_bar)

    //Оповещаем представителя, что представление создано
    presenter.onViewCreated(this)
  }

  /**
   * Колбэк при уничтожении фрагмента
   * В нем освобождаем ресурсы
   */
  override fun onDestroy() {
    super.onDestroy()
    //Удаляем слушателя OnClick кнопки вход
    signInButton.setOnClickListener(null)

    presenter.onDestroy()
  }

  /**
   * Колбэк, вызываемый при нажатии на элемент управления.
   * В текущей ситуации - кнопки Вход
   */
  override fun onClick(view: View?) {
    //Проверяем, что элемент управления - это кнопка Вход
    if (view === signInButton) {
      //Оповещаем представителя, что текст в поле "логин" изменен
      presenter.onLoginTextChanged(loginEditText.text?.toString() ?: "")
      //Оповещаем представителя, что текст в поле "пароль" изменен
      presenter.onPasswordChanged(passwordEditText.text?.toString() ?: "")
      //Оповещаем представителя, что нажата кнопка вход
      lifecycleScope.launch {
        presenter.onEnterButtonPressed()

      }
    }
  }

  override fun setLoginBackground(resourceId: Int) {
    loginEditText.setBackgroundResource(resourceId)
  }

  override fun setPasswordBackground(resourceId: Int) {
    passwordEditText.setBackgroundResource(resourceId)
  }

  override fun setProgressBarVisibility(state: Boolean) {
    signInProgressBar.visibility = if (state)  View.VISIBLE  else View.INVISIBLE
  }

  override fun startMainActivity(parameter: Int) {
    val intent = Intent(context, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
    intent.putExtra(MainActivity.PARAMETER_NAME, parameter)
    startActivity(intent)
    activity?.finish()
  }

  private lateinit var signInProgressBar: ProgressBar
  private lateinit var signInButton: AppCompatButton
  private lateinit var loginEditText: AppCompatEditText
  private lateinit var passwordEditText: AppCompatEditText

  private val presenter = SignInPresenter()

  companion object {
    /**
     * Фабричный метод для создания экземпляра фрагмента
     */
    @JvmStatic
    fun newInstance() =
      SignInFragment()
  }
}