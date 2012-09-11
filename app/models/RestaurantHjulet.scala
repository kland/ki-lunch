package models

import lib.Util

class RestaurantHjulet extends Restaurant {

	def name = "Hjulet"
	
	def url = "http://web.comhem.se/hjulet/matsedel2.html"
	
	override def charset = "ISO-8859-1"

	def parsedDishes(document: String, weekday: String) = {		
		val nextWeekday = Util.nextWeekday(weekday)

		//extract the content between the third occurrence of `weekday' and the third occurence of `nextWeekday'
		val pos1 = (("""(?i)\b""" + weekday + """\b""").r findAllIn document).matchData.toSeq(2).end
		val pos2 = 
			if (weekday != "fredag")
				(("""(?i)\b""" + nextWeekday + """\b""").r findAllIn document).matchData.toSeq(2).start
			else 
				(("""(?i)\bveckans tips\b""").r findAllIn document).matchData.toSeq(0).start
		var documentPart = document.substring(pos1, pos2)

		//replace each newline with a space
		documentPart = documentPart.replace('\n', ' ')
		
		//replace each <br> with a newline
		documentPart = documentPart.replaceAll("(?i)<br>", "\n")

		//remove all remaining tags
		documentPart = documentPart.replaceAll("</?[^>]*>", "")

		//remove junk
		documentPart = documentPart.replaceAll("(?i)veg(&nbsp;)*", "")

		//remove leading and trailing whitespace
		documentPart = documentPart.trim
		
		//split at linebreaks into a list of dishes
		documentPart.split("\n").toList
	}
}
