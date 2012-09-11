package models

import models.{RestaurantHjulet, RestaurantJönsJacob, RestaurantMF}

object Restaurants {

	def all = List(new RestaurantHjulet, new RestaurantJönsJacob, new RestaurantMF)

}
