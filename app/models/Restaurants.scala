package models

//import models.{RestaurantHjulet, RestaurantJönsJacob, RestaurantKönigs, RestaurantMF, RestaurantMollan}

object Restaurants {

	def all = List(
		new RestaurantHjulet, 
		new RestaurantJönsJacob, 
		new RestaurantKönigs, 
		new RestaurantMF,
		new RestaurantMollan
	)
}
