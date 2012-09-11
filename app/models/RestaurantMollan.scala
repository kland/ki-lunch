package models

import lib.Util

class RestaurantMollan extends Restaurant {

	def name = "Mollan Asian Kök"
	
	def url = "http://www.mollanasiankok.se/veckansmeny.html"
	
	override def charset = "ISO-8859-1"

	def parsedDishes(document: String, weekday: String) = {
		val nextWeekday = Util.nextWeekday(weekday)

		//extract the content between the third occurrence of `weekday' and the third occurence of `nextWeekday'
		val pos1 = (("""(?i)\b""" + weekday + """\b""").r findAllIn document).matchData.toSeq(0).end
		val pos2 = 
			if (weekday != "fredag")
				(("""(?i)\b""" + nextWeekday + """\b""").r findAllIn document).matchData.toSeq(0).start
			else 
				(("""(?i)\bSushi ---Se Sushi Meny\b""").r findAllIn document).matchData.toSeq(0).start
		var documentPart = document.substring(pos1, pos2)

		//replace each newline with a space
		documentPart = documentPart.replace('\n', ' ')
		
		//replace each <br> with a newline
		documentPart = documentPart.replaceAll("(?i)<br */?>", "\n")

		//remove all remaining tags
		documentPart = documentPart.replaceAll("</?[^>]*>", "")

		//remove multiple newlines
		documentPart = documentPart.replaceAll("""\n\s*""", "\n")

		//remove leading and trailing whitespace
		documentPart = documentPart.trim
		
		//remove junk
		documentPart = documentPart.replaceAll("""&nbsp;""", "")
		
		//split at letters into a list of dishes
		documentPart.split("""[A-F]\. *""").toList.tail
	}
}
