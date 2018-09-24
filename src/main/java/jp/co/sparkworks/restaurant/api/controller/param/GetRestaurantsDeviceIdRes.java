package jp.co.sparkworks.restaurant.api.controller.param;

import java.util.List;

import jp.co.sparkworks.restaurant.api.dto.RestaurantApiDto;
import lombok.Data;

@Data
public class GetRestaurantsDeviceIdRes extends BaseRes {

    private List<RestaurantApiDto> restaurantDtoList;

}
