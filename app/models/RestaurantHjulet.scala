package models

class RestaurantHjulet extends Restaurant {

	def name = "Hjulet"

	def dishes(weekday: String) = List("soppa", "knödel")
}
