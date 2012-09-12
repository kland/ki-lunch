package models

import lib.Util

class RestaurantJönsJacob extends Restaurant {

	def name = "Jöns Jacob"
	
	def url = "http://gastrogate.com/restaurang/jonsjacob/page/3"

	override def charset = "ISO-8859-1"

	def startPattern(weekday: String) = """(?i)\b""" + weekday + """\b(\w|\s)*"""

	def endPattern(weekday: String) =
		if (weekday != "fredag") 
			startPattern(Util.nextWeekday(weekday))
		else
			"""(?i)<a .*?'Boka bord på din iPhone'"""

	def dishSeparator = "<br */?>"
	
	override def cleanupPattern = """(?m)\d+:-|^\s*(Dagens .*|Soppa|Vegetariskt)\s*$""" //removal of prices and headings

}
