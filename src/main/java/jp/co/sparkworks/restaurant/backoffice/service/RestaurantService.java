package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;

public interface RestaurantService {

	List<RestaurantDto> search();

	void createRestaurant(RestaurantDto restaurantDto);
	
	RestaurantDto getById(Long restaurantId);

	void update(RestaurantDto restaurantDto);

	void delete(Long restaurantId);
}
