package models

import java.io.{BufferedReader, InputStreamReader}
import java.net.URL
import scala.io.Source

abstract class Restaurant {

	def name: String //name of restaurant
	
	def url: String //url for source menu
	
	protected def charset = "UTF-8" //character encoding of source menu

	protected def startPattern(weekday: String): String //regexp matching start of weekday's menu
	
	protected def endPattern(weekday: String): String //regexp matching end of weekday's menu

	protected def dishSeparator: String //regexp matching separator for dishes (e.g. <br>)
	
	protected def cleanupPattern = "" //regexp matching strings that should be removed from the output
	
	
	private def menuPart(document: String, weekday: String): String = { //returns a substring of the source document that contains weekday's menu

		//find position of string matched by start pattern
		val startMatch = startPattern(weekday).r findAllIn document
		if (! startMatch.hasNext) {
			throw new Exception("Start pattern not found: " + startPattern(weekday))
		}
		val startPos = startMatch.matchData.toSeq(0).end
		
		//find position of string matched by end pattern
		val endMatch = endPattern(weekday).r findAllIn document
		if (! endMatch.hasNext) {
			throw new Exception("End pattern not found: " + startPattern(weekday))
		}
		val endPos = endMatch.matchData.toSeq(0).start
		
		//make sure the positions make sense
		if (! ((startPos <= endPos) && (endPos < document.length))) {
			throw new Exception("Invalid or insufficient search patterns")
		}
		
		document.substring(startPos, endPos)
	}
	
	
	def menu(weekday: String): List[String] = { //returns the menu for the weekday
		try {
			//get HTML document from URL
			val inputStream = new URL(url).openStream()		
			val document = Source.fromInputStream(inputStream, charset).mkString("")
			var documentPart = menuPart(document, weekday)		

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
			
		} catch {
			case e: Exception => {
				System.err.printf("In class %s: %s", this.getClass.getName, e)
				List()
			}
		}
	}

}
