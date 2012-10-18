package models

class RestaurantHjulet extends Restaurant {

	def name = "Hjulet"
	
	def url = "http://web.comhem.se/hjulet/matsedel2.html"
	
	override protected def charset = "ISO-8859-1"

	protected def startPattern(weekday: String) = "<h4>(<BR>)?" + weekday.toUpperCase

	protected def endPattern(weekday: String) =
		if (weekday != "fredag") 
			startPattern(lib.Util.nextWeekday(weekday))
		else
			"VECKANS TIPS"

	protected def dishSeparator = "<BR>"
	
	override protected def cleanupPattern = "VEG *(&nbsp;)*"

}
