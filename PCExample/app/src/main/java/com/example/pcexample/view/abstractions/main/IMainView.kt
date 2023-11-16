package com.example.pcexample.view.abstractions.main

/**
 * Интерфейс главного окна
 */
interface IMainView {
  /**
   * Установить заголовок окна
   * @param header текст заголовка
   */
  fun setHeader(header: String)

  /**
   * Перезагрузить список
   */
  fun refreshItems()

  /**
   * Показать диалог
   */
  fun showDialog()
}