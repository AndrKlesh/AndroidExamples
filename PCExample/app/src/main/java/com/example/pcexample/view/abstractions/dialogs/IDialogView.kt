package com.example.pcexample.view.abstractions.dialogs

/**
 * Интерфейс представления диалогового окна
 */
interface IDialogView {
  /**
   * Установить заголовок диалога
   * @param stringResourceId ID строкового ресурса заголовка окна
   */
  fun setTitle(stringResourceId: Int)

  /**
   * Установить заголовок диалога
   * @param title заголовок окна
   */
  fun setTitle(title: String)

  /**
   * Установить иконку заголовка
   * @param resourceDrawableId ID ресурса изображения
   */
  fun setTitleDrawable(resourceDrawableId: Int)

}