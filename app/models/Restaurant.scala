package models

import java.io.{BufferedReader, InputStreamReader}
import java.net.URL
import scala.io.Source

abstract class Restaurant {

	def name: String
	
	def url: String //url for the menu
	
	protected def charset = "UTF-8" //character encoding of the menu at url

	protected def parsedDishes(document: String, weekday: String): List[String]
	
	def dishes(weekday: String): List[String] = {
		//get (HTML) document from URL
		val inputStream = new URL(url).openStream()
		val document = Source.fromInputStream(inputStream, charset).mkString("")
		
		parsedDishes(document, weekday)
	}

}
