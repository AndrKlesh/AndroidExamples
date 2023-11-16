package com.example.pcexample.model

import com.example.pcexample.model.abstractions.meters.IMetersService
import com.example.pcexample.model.abstractions.meters.Meter
import kotlin.math.round
import kotlin.random.Random
import kotlin.random.nextInt

class MetersService : IMetersService {
  override fun getMeters(username: String): List<Meter> = listOf(
    Meter(generateMeterName(), generateIndications()),
    Meter(generateMeterName(), generateIndications()),
    Meter(generateMeterName(), generateIndications()),
    Meter(generateMeterName(), generateIndications()),
    Meter(generateMeterName(), generateIndications()),
    Meter(generateMeterName(), generateIndications()),
  )


  private fun generateMeterName(): String {
    return "C05-" + (1..10).map { Random.nextInt(1..9) }.joinToString("")
  }

  private fun generateIndications(): Double =
    round(Random.nextDouble(0.toDouble(), 255.toDouble()) * 100.0) / 100.0
}