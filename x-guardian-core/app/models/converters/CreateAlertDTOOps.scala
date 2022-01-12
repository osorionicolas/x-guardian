package models.converters

import models.{CreateAlert, CreateAlertDTO}

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object CreateAlertDTOOps {
  implicit class CreateAlertDTOOps(createAlertDTO: CreateAlertDTO) {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    def toDomain() =
      CreateAlert(
        alert = createAlertDTO.alert,
        userId = createAlertDTO.userId,
        createdAt = LocalDateTime.parse(createAlertDTO.createdAt, formatter),
        location = createAlertDTO.location
      )
  }
}
