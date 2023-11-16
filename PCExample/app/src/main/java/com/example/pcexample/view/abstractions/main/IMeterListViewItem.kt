package com.example.pcexample.view.abstractions.main

/**
 * Интерфейс элемента списка устройств
 */
interface IMeterListViewItem {
  /**
   * Установить имя устройства
   * @param name имя устройства
   */
  fun setName(name: String)

  /**
   * Установить показания
   * @param value значение показаний
   */
  fun setIndications(value: String)
}