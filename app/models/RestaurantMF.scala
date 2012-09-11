package models

import lib.Util

class RestaurantMF extends Restaurant {

	def name = "MF"
	
	def url = "http://www.mmcatering.nu/index.php?sajt=mf"

	override def charset = "ISO-8859-1"

	def parsedDishes(document: String, weekday: String) = {
		if (weekday == Util.currentWeekday) {
			//extract the content between the third occurrence of `weekday' and the third occurence of `nextWeekday'
			val pos1 = (("(?i)<a id=\"meny\"[^>]*>").r findAllIn document).matchData.toSeq(0).end
			val pos2 = (("(?i)<img src=\"mmcatering/graphics/mf/startsidan/imorgonServeras.gif\"").r findAllIn document).matchData.toSeq(0).start
			var documentPart = document.substring(pos1, pos2)

			//replace each newline with a space
			documentPart = documentPart.replace('\n', ' ')
		
			//replace each br element with a newline
			documentPart = documentPart.replaceAll("(?i)<br */?>", "\n")

			//remove all remaining tags
			documentPart = documentPart.replaceAll("</?[^>]*>", "")

			//remove leading and trailing whitespace
			documentPart = documentPart.trim
		
			//split at linebreaks into a list of dishes
			documentPart.split("\n").toList
		} else {
			List()
		}
	}
}
