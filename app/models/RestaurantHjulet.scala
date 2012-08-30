package models

import java.io.{BufferedReader, InputStreamReader}
import java.net.URL
import lib.Util

class RestaurantHjulet(override val name: String, override val url: String) extends Restaurant(name, url) {

	def dishes(weekday: String) = List("soppa", "knödel")
}
