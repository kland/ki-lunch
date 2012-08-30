package models

import java.io.{BufferedReader, InputStreamReader}
import java.net.URL
import lib.Util

abstract class Restaurant(val name: String, val url: String) {

	protected def parsedDishes(document: String, weekday: String): List[String]
	
	def dishes(weekday: String): List[String] = {
		//get (HTML) document from URL
		var document = ""
		var reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()))
		var line = reader.readLine
		while (line != null) {
			document += line
			line = reader.readLine
		}
		
		parsedDishes(document, weekday)
	}

}
