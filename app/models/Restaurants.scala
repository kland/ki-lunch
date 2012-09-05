package models

import models.{RestaurantHjulet, RestaurantJönsJacob, RestaurantMF}

object Restaurants {

	def all = List(
		new RestaurantHjulet("Hjulet", "http://web.comhem.se/hjulet/matsedel2.html"),
		new RestaurantJönsJacob("Jöns Jacob", "http://gastrogate.com/restaurang/jonsjacob/page/3"),  
		new RestaurantMF("MF", "http://www.mmcatering.nu/index.php?sajt=mf")
	)

}
