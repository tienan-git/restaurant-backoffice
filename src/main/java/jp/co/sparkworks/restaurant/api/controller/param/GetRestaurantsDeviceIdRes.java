package jp.co.sparkworks.restaurant.api.controller.param;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;
import lombok.Data;

@Data
public class GetRestaurantsDeviceIdRes extends BaseRes {

    private List<RestaurantDto> restaurantDtoList;

}
