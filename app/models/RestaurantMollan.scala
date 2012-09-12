package models

import lib.Util

class RestaurantMollan extends Restaurant {

	def name = "Mollan Asian Kök"
	
	def url = "http://www.mollanasiankok.se/veckansmeny.html"
	
	override protected def charset = "ISO-8859-1"

	protected def startPattern(weekday: String) = """(?i)\b""" + weekday + """\b"""

	protected def endPattern(weekday: String) = 
		if (weekday != "fredag")
			startPattern(Util.nextWeekday(weekday))
		else
			"""(?i)\bSushi ---Se Sushi Meny\b"""

	protected def dishSeparator = """(?i)<p\b[^>]*><font\b[^>]*><span\b[^>]*><span\b[^>]*>[A-Z]\."""

	override protected def cleanupPattern = """(&nbsp;)+"""

}
