package models

class RestaurantMF extends Restaurant {

	def name = "MF"

	def dishes(weekday: String) = List("kött", "fisk")
	
}
