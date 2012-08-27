package models

abstract class Restaurant {

	def name: String
	
	def dishes(weekday: String): List[String]
	
}
