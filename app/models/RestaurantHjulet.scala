package models

//import scala.util.matching.Regex
import lib.Util

class RestaurantHjulet(override val name: String, override val url: String) extends Restaurant(name, url) {

	def parsedDishes(document: String, weekday: String) = {		
		val nextWeekday = Util.nextWeekday(weekday)

		//extract the content between the third occurrence of `weekday' and the third occurence of `nextWeekday'
		val pos1 = (("""(?i)\b""" + weekday + """\b""").r findAllIn document).matchData.toSeq(2).end
		val pos2 = (("""(?i)\b""" + nextWeekday + """\b""").r findAllIn document).matchData.toSeq(2).start
		var documentPart = document.substring(pos1, pos2)
				
		//replace each newline with a space
		documentPart = documentPart.replace('\n', ' ')
		
		//replace each <br> with a newline
		documentPart = documentPart.replaceAll("(?i)<br>", "\n")

		//remove all remaining tags
		documentPart = documentPart.replaceAll("</?[^>]*>", "")

		//remove leading and trailing whitespace
		documentPart = documentPart.trim
		
		//split at linebreaks into a list of dishes
		documentPart.split("\n").toList
		
		//val matches = ("""(?i)\bfredag\b""".r findAllIn document).matchData map (_.end)
		//val matches = """(?i)\btisdag\b""".r findAllIn document
		//println(matches.matchData.toSeq.last)
		//List("soppa", "knödel")
	}
}
