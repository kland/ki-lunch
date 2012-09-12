package models

import lib.Util

class RestaurantMF extends Restaurant {

	def name = "MF"
	
	def url = "http://www.mmcatering.nu/index.php?sajt=mf"

	override protected def charset = "ISO-8859-1"
	
	protected def startPattern(weekday: String) = "(?i)<a id=\"meny\"[^>]*>"

	protected def endPattern(weekday: String) = "(?i)<img src=\"mmcatering/graphics/mf/startsidan/imorgonServeras.gif\""

	protected def dishSeparator = "<br */?>"

}
