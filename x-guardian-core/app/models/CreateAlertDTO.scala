package models

case class CreateAlertDTO(alert: AlertTypeDTO, userId: String, createdAt: String, location: Location)

case class AlertTypeDTO(id: String, name: String)
