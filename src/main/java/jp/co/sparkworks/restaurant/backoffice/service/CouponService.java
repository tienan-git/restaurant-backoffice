package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;

public interface CouponService {

	List<CouponDto> search();

	CouponDto create(CouponDto couponDto);

	CouponDto getById(Long couponId);

	void update(CouponDto couponDto);

	void delete(Long couponId);

}
