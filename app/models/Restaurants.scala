package models

import models.{RestaurantHjulet, RestaurantMF}

object Restaurants {

	def all = List(
		new RestaurantHjulet("Hjulet", "http://web.comhem.se/hjulet/matsedel2.html"), 
		new RestaurantMF("MF", "http://www.mmcatering.nu/index.php?sajt=mf")
	)

}
