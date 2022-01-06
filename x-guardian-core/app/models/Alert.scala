package models

import java.time.LocalDateTime

// TODO review location type
case class Alert(`type`: AlertType, userId: String, createdAt: LocalDateTime, location: Location)
