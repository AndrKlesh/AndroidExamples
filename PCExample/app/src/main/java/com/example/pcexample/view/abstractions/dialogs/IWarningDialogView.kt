package com.example.pcexample.view.abstractions.dialogs

/**
 * Интерфейс диалога - предупреждения
 */
interface IWarningDialogView : IDialogView {
  /**
   * Установить сообщение
   * @param messageId ID строкового ресурса сообщения
   */
  fun setWarningMessage(messageId: Int)

  /**
   * Установить сообщение
   * @param templateId ID строкового ресурса шаблона сообщения
   * @param message сообщение
   */
  fun setWarningMessage(templateId: Int, message: String)

  /**
   * Установить сообщение
   * @param message сообщение
   */
  fun setWarningMessage(message: String)

  /**
   * Установить изображение к сообщению
   * @param drawableId ID ресурса изображения
   */
  fun setWarningDrawable(drawableId: Int)
}