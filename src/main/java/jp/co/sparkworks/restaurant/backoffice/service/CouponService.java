package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;
import jp.co.sparkworks.restaurant.backoffice.dto.CouponHoldDto;
import jp.co.sparkworks.restaurant.backoffice.dto.CouponSearchDto;

public interface CouponService {

	List<CouponHoldDto> searchAll(CouponSearchDto couponSearchDto);
}
