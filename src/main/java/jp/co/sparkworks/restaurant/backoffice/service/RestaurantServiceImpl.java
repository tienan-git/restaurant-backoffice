package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sparkworks.restaurant.backoffice.dao.RestaurantCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.RestaurantDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Restaurant;
import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantCustomDao restaurantCustomDao;

	@Autowired
	RestaurantDao restaurantDao;

	@Override
	public List<RestaurantDto> search() {

		List<Restaurant> restaurantList = restaurantCustomDao.selectAll();
		List<RestaurantDto> restaurantDtoList = new ArrayList<RestaurantDto>();
		for (Restaurant restaurant : restaurantList) {

			RestaurantDto restaurantDto = new RestaurantDto();
			restaurantDto.setRestaurantId(restaurant.getRestaurantId());
			restaurantDto.setRestaurantName(restaurant.getRestaurantName());
			restaurantDto.setRestaurantManager(restaurant.getRestaurantManager());
			restaurantDto.setRestaurantPhone(restaurant.getRestaurantPhone());
			restaurantDto.setRestaurantOpenTime(restaurant.getRestaurantOpenTime());
			restaurantDto.setRestaurantImageUrl(restaurant.getRestaurantImageUrl());
			restaurantDto.setRestaurantUrl(restaurant.getRestaurantUrl());
			restaurantDto.setLatitude(restaurant.getLatitude());
			restaurantDto.setLongitude(restaurant.getLongitude());
			restaurantDto.setRestaurantStatus(restaurant.getRestaurantStatus());

			restaurantDtoList.add(restaurantDto);
		}
		return restaurantDtoList;
	}

	@Override
	public void createRestaurant(RestaurantDto restaurantDto) {

		// DTO->Entity
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantId(restaurantDto.getRestaurantId());
		restaurant.setRestaurantName(restaurantDto.getRestaurantName());
		restaurant.setRestaurantManager(restaurantDto.getRestaurantManager());
		restaurant.setRestaurantPhone(restaurantDto.getRestaurantPhone());
		restaurant.setRestaurantOpenTime(restaurantDto.getRestaurantOpenTime());
		restaurant.setRestaurantImageUrl(restaurantDto.getRestaurantImageUrl());
		restaurant.setRestaurantUrl(restaurantDto.getRestaurantUrl());
		restaurant.setLatitude(restaurantDto.getLatitude());
		restaurant.setLongitude(restaurantDto.getLongitude());
		restaurant.setRestaurantStatus(restaurantDto.getRestaurantStatus());

		// DB access
		restaurantDao.insert(restaurant);
	}

}
