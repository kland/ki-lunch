package models

class RestaurantMF(override val name: String, override val url: String) extends Restaurant(name, url) {

	def parsedDishes(document: String, weekday: String) = List("kött", "fisk")
	
}
