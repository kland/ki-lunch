package models

import lib.Util

class RestaurantKönigs extends Restaurant {

	def name = "Königs"
	
	def url = "http://restaurangkonigs.se"

	def parsedDishes(document: String, weekday: String) = {
		val nextWeekday = Util.nextWeekday(weekday)

		//extract the content between the third occurrence of `weekday' and the third occurence of `nextWeekday'
		val pos1 = (("""(?i)<h3><strong>""" + weekday + """:</strong></h3>(\w|\s)*""").r findAllIn document).matchData.toSeq(0).end
		val pos2 = 
			if (weekday != "fredag")
				(("""(?i)<h3>""" + nextWeekday + """:</h3>""").r findAllIn document).matchData.toSeq(0).start
			else 
				(("""(?i)<strong>Dagens rätt\b""").r findAllIn document).matchData.toSeq(0).start
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
		
		//split at linebreaks into a list of dishes
		documentPart.split("\n").toList
	}
}
