package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sparkworks.restaurant.backoffice.dao.CouponCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Coupon;
import jp.co.sparkworks.restaurant.backoffice.db.entity.CouponWithCount;
import jp.co.sparkworks.restaurant.backoffice.db.entity.LotteryWithCount;
import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;
import jp.co.sparkworks.restaurant.backoffice.dto.CouponHoldDto;
import jp.co.sparkworks.restaurant.backoffice.dto.CouponSearchDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotterySearchDto;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	CouponCustomDao couponCustomDao;
	@Transactional

	@Override
	public List<CouponHoldDto> searchAll(CouponSearchDto couponSearchDto) {

		List<CouponWithCount> couponList = couponCustomDao.selectByCriteria(couponSearchDto);
		List<CouponHoldDto> couponDtoList = new ArrayList<CouponHoldDto>();
		for (CouponWithCount coupon : couponList) {
			CouponHoldDto couponDto = new CouponHoldDto();
			couponDto.setCouponId(coupon.getCouponId());
			couponDto.setRestaurantId(coupon.getRestaurantId());
			couponDto.setTitle(coupon.getTitle());
			couponDto.setDetail(coupon.getDetail());
			couponDto.setStartDate(coupon.getStartDate());
			couponDto.setEndDate(coupon.getEndDate());
			couponDto.setTotalAmount(coupon.getTotalAmount());
			couponDto.setCount(coupon.getCount());
			couponDtoList.add(couponDto);
		}

		return couponDtoList;
	}


	
}
