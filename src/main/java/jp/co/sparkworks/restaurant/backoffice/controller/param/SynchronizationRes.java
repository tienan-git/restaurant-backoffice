package jp.co.sparkworks.restaurant.backoffice.controller.param;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;
import lombok.Data;

@Data
public class SynchronizationRes extends BaseRes {

    private List<CouponDto> couponDtoList;

}
