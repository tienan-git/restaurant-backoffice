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
			restaurantDto.setRestaurantManager(restaurant.getManager());
			restaurantDto.setRestaurantPhone(restaurant.getTelephonePhone());
			restaurantDto.setRestaurantOpenTime(restaurant.getBusinessHours());
			restaurantDto.setRestaurantImageUrl(restaurant.getImageUrl());
			restaurantDto.setRestaurantUrl(restaurant.getSiteUrl());
			restaurantDto.setLatitude(restaurant.getLatitude());
			restaurantDto.setLongitude(restaurant.getLongitude());
			restaurantDto.setRestaurantStatus(restaurant.getStatus());

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
		restaurant.setManager(restaurantDto.getRestaurantManager());
		restaurant.setTelephonePhone(restaurantDto.getRestaurantPhone());
		restaurant.setBusinessHours(restaurantDto.getRestaurantOpenTime());
		restaurant.setImageUrl(restaurantDto.getRestaurantImageUrl());
		restaurant.setSiteUrl(restaurantDto.getRestaurantUrl());
		restaurant.setLatitude(restaurantDto.getLatitude());
		restaurant.setLongitude(restaurantDto.getLongitude());
		restaurant.setStatus(restaurantDto.getRestaurantStatus());

		// DB access
		restaurantDao.insert(restaurant);
	}

}
