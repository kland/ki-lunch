package models

import lib.Util

class RestaurantMollan extends Restaurant {

	def name = "Mollan Asian Kök"
	
	def url = "http://www.mollanasiankok.se/veckansmeny.html"
	
	override def charset = "ISO-8859-1"

	def parseBegin(weekday: String) = """(?i)\b""" + weekday + """\b"""

	def parseEnd(weekday: String) = 
		if (weekday != "fredag")
			parseBegin(Util.nextWeekday(weekday))
		else
			"""(?i)\bSushi ---Se Sushi Meny\b"""

	def dishSeparator = """(?i)<p\b[^>]*><font\b[^>]*><span\b[^>]*><span\b[^>]*>[A-Z]\."""

	override def cleanupPattern = """(&nbsp;)+"""

}
