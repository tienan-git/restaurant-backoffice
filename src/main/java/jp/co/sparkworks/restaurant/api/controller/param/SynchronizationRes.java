package jp.co.sparkworks.restaurant.api.controller.param;

import java.util.List;

import jp.co.sparkworks.restaurant.api.dto.CouponApiDto;
import lombok.Data;

@Data
public class SynchronizationRes extends BaseRes {

    private List<CouponApiDto> couponDtoList;

}
