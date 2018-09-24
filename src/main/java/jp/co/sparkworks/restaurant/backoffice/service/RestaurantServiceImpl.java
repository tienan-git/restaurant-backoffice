package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sparkworks.restaurant.backoffice.constant.ErrorCodeConstant;
import jp.co.sparkworks.restaurant.backoffice.dao.RestaurantCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.RestaurantDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Restaurant;
import jp.co.sparkworks.restaurant.backoffice.db.entity.User;
import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;
import jp.co.sparkworks.restaurant.backoffice.exception.BusinessException;

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
			restaurantDto.setManager(restaurant.getManager());
			restaurantDto.setTelephonePhone(restaurant.getTelephonePhone());
			restaurantDto.setBusinessHours(restaurant.getBusinessHours());
			restaurantDto.setSiteUrl(restaurant.getSiteUrl());
			restaurantDto.setImageUrl(restaurant.getImageUrl());
			restaurantDto.setLatitude(restaurant.getLatitude());
			restaurantDto.setLongitude(restaurant.getLongitude());
			restaurantDto.setStatus(restaurant.getRestaurantStatus());
			restaurantDto.setAddress(restaurant.getAddress());
			restaurantDto.setMemo(restaurant.getMemo());
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
		restaurant.setManager(restaurantDto.getManager());
		restaurant.setTelephonePhone(restaurantDto.getTelephonePhone());
		restaurant.setBusinessHours(restaurantDto.getBusinessHours());
		restaurant.setSiteUrl(restaurantDto.getSiteUrl());
		restaurant.setImageUrl(restaurantDto.getImageUrl());
		restaurant.setLatitude(restaurantDto.getLatitude());
		restaurant.setLongitude(restaurantDto.getLongitude());
		restaurant.setRestaurantStatus(restaurantDto.getStatus());
		restaurant.setAddress(restaurantDto.getAddress());
		restaurant.setMemo(restaurantDto.getMemo());
		// DB access
		restaurantDao.insert(restaurant);
	}

	@Override
	public RestaurantDto getById(Long restaurantId) {

		Restaurant restaurant = restaurantDao.selectById(restaurantId);
		if (restaurant == null) {
		}
		RestaurantDto restaurantDto = new RestaurantDto();
		restaurantDto.setRestaurantId(restaurant.getRestaurantId());
		restaurantDto.setRestaurantName(restaurant.getRestaurantName());
		restaurantDto.setManager(restaurant.getManager());
		restaurantDto.setTelephonePhone(restaurant.getTelephonePhone());
		restaurantDto.setBusinessHours(restaurant.getBusinessHours());
		restaurantDto.setSiteUrl(restaurant.getSiteUrl());
		restaurantDto.setImageUrl(restaurant.getImageUrl());
		restaurantDto.setLatitude(restaurant.getLatitude());
		restaurantDto.setLongitude(restaurant.getLongitude());
		restaurantDto.setStatus(restaurant.getRestaurantStatus());
		restaurantDto.setAddress(restaurant.getAddress());
		restaurantDto.setMemo(restaurant.getMemo());

		return restaurantDto;
	}

	@Override
	public void update(RestaurantDto restaurantDto) {

		Restaurant restaurant = restaurantDao.selectById(restaurantDto.getRestaurantId());
		// Null check
		if (restaurant == null) {
		}

		restaurant.setRestaurantId(restaurantDto.getRestaurantId());
		restaurant.setRestaurantName(restaurantDto.getRestaurantName());
		restaurant.setManager(restaurantDto.getManager());
		restaurant.setTelephonePhone(restaurantDto.getTelephonePhone());
		restaurant.setBusinessHours(restaurantDto.getBusinessHours());
		restaurant.setSiteUrl(restaurantDto.getSiteUrl());
		restaurant.setImageUrl(restaurantDto.getImageUrl());
		restaurant.setLatitude(restaurantDto.getLatitude());
		restaurant.setLongitude(restaurantDto.getLongitude());
		restaurant.setRestaurantStatus(restaurantDto.getStatus());
		restaurant.setAddress(restaurantDto.getAddress());
		restaurant.setMemo(restaurantDto.getMemo());

		restaurantDao.update(restaurant);

	}

	@Override
	public void delete(Long restaurantId) {
		Restaurant restaurant = restaurantDao.selectById(restaurantId);
		if (restaurant == null) {
		}
		restaurantDao.delete(restaurant);

	}

}
