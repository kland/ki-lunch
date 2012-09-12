package models

import lib.Util

class RestaurantHjulet extends Restaurant {

	def name = "Hjulet"
	
	def url = "http://web.comhem.se/hjulet/matsedel2.html"
	
	override def charset = "ISO-8859-1"

	def startPattern(weekday: String) = "<h4>(<BR>)?" + weekday.toUpperCase

	def endPattern(weekday: String) =
		if (weekday != "fredag") 
			startPattern(Util.nextWeekday(weekday))
		else
			"VECKANS TIPS"

	def dishSeparator = "<BR>"
	
	override def cleanupPattern = "VEG(&nbsp;)*"

}
