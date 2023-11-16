package com.example.pcexample.view.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.lifecycleScope
import com.example.pcexample.R
import com.example.pcexample.presentation.login.SignUpPresenter
import com.example.pcexample.view.abstractions.login.ISignUpView
import com.example.pcexample.view.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * [Fragment] Регистрации в системе
 * Используйте [SignUpFragment.newInstance] для создания фрагмента
 * Его устройство аналогично [SignInFragment]
 */
class SignUpFragment :
  Fragment(),
  OnClickListener,
  ISignUpView {
  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_sign_up, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    signUpButton = view.findViewById(R.id.sign_up_button)
    signUpButton.setOnClickListener(this)

    loginEditText = view.findViewById(R.id.sign_up_username_edit_text)
    passwordEditText = view.findViewById(R.id.sign_up_password_edit_text)
    phoneEditText = view.findViewById(R.id.sign_up_phone_edit_text)
    emailEditText = view.findViewById(R.id.sign_up_email_edit_text)

    presenter.onViewCreated(this)
  }

  override fun onDestroy() {
    super.onDestroy()
    signUpButton.setOnClickListener(null)
    presenter.onDestroy()
  }

  override fun onClick(view: View?) {
    if (view === signUpButton) {
      presenter.onLoginTextChanged(loginEditText.text?.toString() ?: "")
      presenter.onPasswordChanged(passwordEditText.text?.toString() ?: "")
      presenter.onEmailChanged(emailEditText.text?.toString() ?: "")
      presenter.onPhoneChanged(phoneEditText.text?.toString() ?: "")
       lifecycleScope.launch {
        presenter.onRegisterButtonClick()
      }
    }
  }

  override fun setLoginBackground(resourceId: Int) {
    loginEditText.setBackgroundResource(resourceId)
  }

  override fun setPasswordBackground(resourceId: Int) {
    passwordEditText.setBackgroundResource(resourceId)
  }

  override fun setEmailBackground(resourceId: Int) {
    emailEditText.setBackgroundResource(resourceId)
  }

  override fun setPhoneBackground(resourceId: Int) {
    phoneEditText.setBackgroundResource(resourceId)
  }

  override fun startMainActivity() {
    val intent = Intent(context, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
    activity?.finish()
  }

  private lateinit var signUpButton: AppCompatButton
  private lateinit var loginEditText: AppCompatEditText
  private lateinit var passwordEditText: AppCompatEditText
  private lateinit var phoneEditText: AppCompatEditText
  private lateinit var emailEditText: AppCompatEditText
  private val presenter = SignUpPresenter()

  companion object {
    /**
     * Фабричный метод для создания экземпляра фрагмента
     */
    @JvmStatic
    fun newInstance() =
      SignUpFragment()
  }
}