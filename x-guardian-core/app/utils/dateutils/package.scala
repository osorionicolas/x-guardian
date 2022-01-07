package utils

import java.time.format.DateTimeFormatter

package object dateutils {
  val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
}
