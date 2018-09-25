package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sparkworks.restaurant.backoffice.dao.CouponCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Coupon;
import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	CouponCustomDao couponCustomDao;

	@Override
	public List<CouponDto> getList() {

		List<Coupon> couponList = couponCustomDao.selectAll();
		List<CouponDto> couponDtoList = new ArrayList<CouponDto>();
		for (Coupon coupon : couponList) {
			CouponDto couponDto = new CouponDto();
			couponDto.setCouponId(coupon.getCouponId());
			couponDto.setRestaurantId(coupon.getRestaurantId());
			couponDto.setTitle(coupon.getTitle());
			couponDto.setDetail(coupon.getDetail());
			couponDto.setStartDate(coupon.getStartDate());
			couponDto.setEndDate(coupon.getEndDate());
			couponDto.setTotalAmount(coupon.getTotalAmount());
			couponDtoList.add(couponDto);
		}

		return couponDtoList;
	}

}
