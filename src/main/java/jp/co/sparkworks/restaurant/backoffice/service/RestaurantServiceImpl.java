package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sparkworks.restaurant.backoffice.dao.RestaurantCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Restaurant;
import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantCustomDao restaurantCustomDao;
	
	@Override
	public List<RestaurantDto> search() {

		List<Restaurant> restaurantList = restaurantCustomDao.selectAll();
		List<RestaurantDto> restaurantDtoList = new ArrayList<RestaurantDto>();
		for(Restaurant restaurant: restaurantList) {
			
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


}
