package models

import lib.Util

class RestaurantJönsJacob extends Restaurant {

	def name = "Jöns Jacob"
	
	def url = "http://gastrogate.com/restaurang/jonsjacob/page/3"

	override def charset = "ISO-8859-1"

	def parsedDishes(document: String, weekday: String) = {
		val nextWeekday = Util.nextWeekday(weekday)

		//extract the content between the third occurrence of `weekday' and the third occurence of `nextWeekday'
		val pos1 = (("""(?i)\b""" + weekday + """\b(\w|\s)*""").r findAllIn document).matchData.toSeq(0).end
		val pos2 = 
			if (weekday != "friday")
				(("""(?i)\b""" + nextWeekday + """\b""").r findAllIn document).matchData.toSeq(0).start
			else 
				(("""(?i)<a .*?'Boka bord på din iPhone'""").r findAllIn document).matchData.toSeq(0).start
		var documentPart = document.substring(pos1, pos2)

		//replace each newline with a space
		documentPart = documentPart.replace('\n', ' ')
		
		//replace each <br> with a newline
		documentPart = documentPart.replaceAll("(?i)<br>", "\n")

		//remove all remaining tags
		documentPart = documentPart.replaceAll("</?[^>]*>", "")

		//remove prices
		documentPart = documentPart.replaceAll("""\d+:-""", "")
		
		//remove "dagens" strings
		documentPart = documentPart.replaceAll("""(?i)dagens (rätt|nyckelhål)""", "")

		//remove multiple newlines
		documentPart = documentPart.replaceAll("""\n\s*""", "\n")

		//remove leading and trailing whitespace
		documentPart = documentPart.trim
		
		//split at linebreaks into a list of dishes
		documentPart.split("\n").toList
	}
}
