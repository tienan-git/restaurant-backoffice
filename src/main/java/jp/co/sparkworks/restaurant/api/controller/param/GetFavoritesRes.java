package jp.co.sparkworks.restaurant.api.controller.param;

import java.util.List;

import jp.co.sparkworks.restaurant.api.dto.CouponAndRestaurantApiDto;
import lombok.Data;

@Data
public class GetFavoritesRes extends BaseRes {

    private List<CouponAndRestaurantApiDto> restaurants;

}
