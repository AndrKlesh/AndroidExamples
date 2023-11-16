package com.example.pcexample.presentation.abstractions

/**
 * Базовый интерфейс представителя
 */
interface IPresenter<TView> {
  /**
   * Отклик на событие создания представления
   */
  fun onViewCreated(view: TView)

  /**
   * Отклик на событие старта представления
   */
  fun onStart() {

  }

  /**
   * Отклик на событие возобновления представления
   */
  fun onResume() {

  }

  /**
   * Отклик на событие паузу представления
   */
  fun onPause() {

  }

  /**
   * Отклик на событие остановку представления
   */
  fun onStop() {

  }

  /**
   * Отклик на событие удаления представления
   */
  fun onDestroy()
}