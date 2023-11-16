package com.example.pcexample.model.abstractions.meters

/**
 * Сервис работы со счетчиками
 */
interface IMetersService {
  /**
   * Получить счетчики пользователя
   * @param username Имя пользователя
   */
  fun getMeters(username: String) : List<Meter>
}