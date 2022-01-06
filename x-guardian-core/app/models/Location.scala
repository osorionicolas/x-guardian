package models

case class Location(coords: Coords)

case class Coords(latitude: Long, longitude: Long)

object Location {

  def apply(latitude: Long, longitude: Long): Location =
    Location(Coords(latitude, longitude))
}
