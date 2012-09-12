package models

import java.io.{BufferedReader, InputStreamReader}
import java.net.URL
import scala.io.Source

abstract class Restaurant {

	def name: String
	
	def url: String //url for the menu
	
	protected def charset = "UTF-8" //character encoding of the menu at url

	protected def parseBegin(weekday: String): String
	
	protected def parseEnd(weekday: String): String

	protected def dishSeparator: String
	
	protected def cleanupPattern = ""
	
	def dishes(weekday: String): List[String] = {
		//get HTML document from URL
		val inputStream = new URL(url).openStream()
		val document = Source.fromInputStream(inputStream, charset).mkString("")
		
		//extract the content between parseBegin and parseEnd
		val pos1 = (parseBegin(weekday).r findAllIn document).matchData.toSeq(0).end
		val pos2 = (parseEnd(weekday).r findAllIn document).matchData.toSeq(0).start
		var documentPart = document.substring(pos1, pos2)

		//replace each newline with a space
		documentPart = documentPart.replace('\n', ' ')

		//replace each dishSeparator with a newline
		documentPart = documentPart.replaceAll(dishSeparator, "\n")

		//remove all remaining tags
		documentPart = documentPart.replaceAll("</?[^>]*>", "")

		//do subclass dependent cleanup
		if (cleanupPattern != "") {
			documentPart = documentPart.replaceAll(cleanupPattern, "")
		}

		//normalize newlines
		documentPart = documentPart.replaceAll("""\s*\n\s*""", "\n")

		//remove leading and trailing whitespace
		documentPart = documentPart.trim

		//split at linebreaks into a list of dishes
		documentPart.split("\n").toList
	}

}
