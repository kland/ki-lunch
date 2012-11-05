package models

class RestaurantMF extends Restaurant {

	def name = "MF"
	
	def url = "http://mmcatering.nu/mfs-kafe-kok/meny"

	protected def startPattern(weekday: String) = "(?i)<h6>" + weekday + "</h6>"

	protected def endPattern(weekday: String) =
		if (weekday != "fredag") 
			startPattern(lib.Util.nextWeekday(weekday))
		else
			"<div id=\"primary\">"

	protected def dishSeparator = """<p>\s*</p>"""

}
