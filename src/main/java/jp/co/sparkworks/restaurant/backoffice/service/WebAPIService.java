package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;
import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;

public interface WebAPIService {

    List<CouponDto> synchronization(String deviceId, String nickName);

    List<RestaurantDto> getRestaurants();
}
