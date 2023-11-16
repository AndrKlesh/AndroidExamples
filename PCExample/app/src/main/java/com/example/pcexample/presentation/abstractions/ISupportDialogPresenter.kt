package com.example.pcexample.presentation.abstractions

import com.example.pcexample.view.abstractions.dialogs.IDialogView

/**
 * Интерфейс представителя диалога
 */
interface ISupportDialogPresenter<TDialogView : IDialogView> {
  /**
   * Отклик на создание диалога
   */
  fun onDialogCreate(view: TDialogView)

  /**
   * Отклик на удаление диалога
   */
  fun onDialogDestroy()

  /**
   * Отклик на событие нажатия кнопки OK
   */
  fun onOkButtonClick()

  /**
   * Отклик на событие нажатия кнопки Отмена
   */
  fun onCancelButtonClick()
}