package models

import java.time.LocalDateTime

// TODO review location type
case class CreateAlert(`type`: Int, userId: String, createdAt: LocalDateTime, location: String)
