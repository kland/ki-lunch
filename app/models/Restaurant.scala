package models

abstract class Restaurant(val name: String, val url: String) {
	
	def dishes(weekday: String): List[String]
	
}
