package utils

import net.logstash.logback.marker.{LogstashMarker, Markers}
import play.api.MarkerContext
import play.api.mvc.Request
import utils.MapMarkerContext.UOW

import java.util.UUID
import scala.jdk.CollectionConverters._
import scala.collection.mutable.{Map => MMap}

class MapMarkerContext(val map: MMap[String, String], val uow: String) extends MarkerContext {

  map ++= MMap(UOW -> uow)

  def marker: Option[LogstashMarker] = Some(Markers.appendEntries(map.asJava))
}

object MapMarkerContext {
  val UOW       = "uow"
  val USER_ID   = "userId"
  val LATITUDE  = "latitude"
  val LONGITUDE = "longitude"

  private def newUow: String = UUID.randomUUID().toString

  def fromRequest(map: MMap[String, String] = MMap.empty)(implicit request: Request[_]): MapMarkerContext =
    new MapMarkerContext(map, request.headers.get(UOW).getOrElse(newUow))

}
