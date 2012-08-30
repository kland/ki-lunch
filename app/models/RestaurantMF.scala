package models

class RestaurantMF(override val name: String, override val url: String) extends Restaurant(name, url) {

	def dishes(weekday: String) = List("kött", "fisk")
	
}
