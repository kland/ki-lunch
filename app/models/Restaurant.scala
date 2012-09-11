package models

import java.io.{BufferedReader, InputStreamReader}
import java.net.URL
import lib.Util
import scala.io.Source

abstract class Restaurant {

	def name: String
	
	def url: String //url for the menu
	
	protected def charset = "UTF-8" //character encoding of the menu at url

	protected def parsedDishes(document: String, weekday: String): List[String]
	
	def dishes(weekday: String): List[String] = {
		//get (HTML) document from URL
		var document = ""
		var reader = new BufferedReader(new InputStreamReader(new URL(url).openStream(), charset))
		var line = reader.readLine
		while (line != null) {
			document += line
			line = reader.readLine
		}
		
		parsedDishes(document, weekday)
	}

}
