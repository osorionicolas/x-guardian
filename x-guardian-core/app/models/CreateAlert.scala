package models

import java.time.LocalDateTime

case class CreateAlert(alert: AlertTypeDTO, userId: String, createdAt: LocalDateTime, location: Location)
