package models

import models.{RestaurantHjulet, RestaurantJönsJacob, RestaurantKönigs, RestaurantMF}

object Restaurants {

	def all = List(
		new RestaurantHjulet, 
		new RestaurantJönsJacob, 
		new RestaurantKönigs, 
		new RestaurantMF
	)
}
