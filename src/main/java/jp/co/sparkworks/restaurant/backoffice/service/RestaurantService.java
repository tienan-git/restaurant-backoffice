package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;

public interface RestaurantService {

	List<RestaurantDto> search();
}
