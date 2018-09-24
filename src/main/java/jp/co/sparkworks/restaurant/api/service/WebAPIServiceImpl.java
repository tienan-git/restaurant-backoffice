package jp.co.sparkworks.restaurant.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import jp.co.sparkworks.restaurant.api.dao.CouponCustomApiDao;
import jp.co.sparkworks.restaurant.api.dao.LotteryCustomApiDao;
import jp.co.sparkworks.restaurant.api.dao.RestaurantCustomApiDao;
import jp.co.sparkworks.restaurant.api.db.entity.CouponAndRestaurant;
import jp.co.sparkworks.restaurant.api.db.entity.LotteryApplication;
import jp.co.sparkworks.restaurant.api.db.entity.LotteryWithApplicationCount;
import jp.co.sparkworks.restaurant.api.dto.CouponAndRestaurantApiDto;
import jp.co.sparkworks.restaurant.api.dto.FeedbackApiDto;
import jp.co.sparkworks.restaurant.api.dto.LotteryApiDto;
import jp.co.sparkworks.restaurant.api.dto.LotteryApplicationApiDto;
import jp.co.sparkworks.restaurant.backoffice.dao.CustomerCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.CouponHoldDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.CustomerDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.FeedbackDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.CouponHold;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Customer;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Feedback;
import jp.co.sparkworks.restaurant.backoffice.dto.CustomerDto;
import jp.co.sparkworks.restaurant.backoffice.enums.CouponHoldStatus;
import jp.co.sparkworks.restaurant.backoffice.enums.DateTimeFormatter;
import jp.co.sparkworks.restaurant.backoffice.service.CustomerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WebAPIServiceImpl implements WebAPIService {

	@Autowired
	CustomerDao customerDao;

	@Autowired
	FeedbackDao feedbackDao;

	@Autowired
	CustomerCustomDao customerCustomDao;

	@Autowired
	LotteryCustomApiDao lotteryCustomApiDao;

	@Autowired
	CustomerService customerService;

	@Autowired
	CouponCustomApiDao couponCustomApiDao;

	@Autowired
	CouponHoldDao couponHoldDao;

	@Autowired
	RestaurantCustomApiDao restaurantCustomApiDao;

	@Override
	@Transactional
	public List<CouponAndRestaurantApiDto> postSynchronization(String deviceId, String nickName) {

		// まず、ニックネーム設定
		Customer customer = selectOrCreateCustomer(deviceId);
		customer.setNickName(nickName);
		customerDao.update(customer);

		// あと、クーポン情報返す
		List<CouponAndRestaurant> couponAndRestaurantList = couponCustomApiDao.selectByDeviceId(deviceId);
		List<CouponAndRestaurantApiDto> couponAndRestaurantApiDtoList = new ArrayList<CouponAndRestaurantApiDto>();
		for (CouponAndRestaurant couponAndRestaurant : couponAndRestaurantList) {
			CouponAndRestaurantApiDto couponAndRestaurantApiDto = new CouponAndRestaurantApiDto();
			couponAndRestaurantApiDto.setCouponId(couponAndRestaurant.getCouponId());
			couponAndRestaurantApiDto.setCouponTitle(couponAndRestaurant.getCouponTitle());
			couponAndRestaurantApiDto.setCouponsDetail(couponAndRestaurant.getCouponsDetail());
			couponAndRestaurantApiDto.setCouponsEndDate(couponAndRestaurant.getCouponsEndDate());
			couponAndRestaurantApiDto.setRestaurantId(couponAndRestaurant.getRestaurantId());
			couponAndRestaurantApiDto.setRestaurantName(couponAndRestaurant.getRestaurantName());
			couponAndRestaurantApiDto.setRestaurantAddress(couponAndRestaurant.getRestaurantAddress());
			couponAndRestaurantApiDto.setRestaurantPhoneNumber(couponAndRestaurant.getRestaurantPhoneNumber());
			couponAndRestaurantApiDto.setRestaurantBusinessHours(couponAndRestaurant.getRestaurantBusinessHours());
			couponAndRestaurantApiDto.setRestaurantImageUrl(couponAndRestaurant.getRestaurantImageUrl());
			couponAndRestaurantApiDto.setRestaurantLatitude(couponAndRestaurant.getRestaurantLatitude());
			couponAndRestaurantApiDto.setRestaurantLongitude(couponAndRestaurant.getRestaurantLongitude());

			couponAndRestaurantApiDtoList.add(couponAndRestaurantApiDto);
		}

		return couponAndRestaurantApiDtoList;
	}

	@Override
	public List<CouponAndRestaurantApiDto> getRestaurants() {

		List<CouponAndRestaurant> couponAndRestaurantList = restaurantCustomApiDao.selectAll();
		List<CouponAndRestaurantApiDto> couponAndRestaurantApiDtoList = new ArrayList<CouponAndRestaurantApiDto>();
		for (CouponAndRestaurant couponAndRestaurant : couponAndRestaurantList) {
			CouponAndRestaurantApiDto couponAndRestaurantApiDto = new CouponAndRestaurantApiDto();
			couponAndRestaurantApiDto.setCouponId(couponAndRestaurant.getCouponId());
			couponAndRestaurantApiDto.setCouponTitle(couponAndRestaurant.getCouponTitle());
			couponAndRestaurantApiDto.setCouponsDetail(couponAndRestaurant.getCouponsDetail());
			couponAndRestaurantApiDto.setCouponsEndDate(couponAndRestaurant.getCouponsEndDate());
			couponAndRestaurantApiDto.setRestaurantId(couponAndRestaurant.getRestaurantId());
			couponAndRestaurantApiDto.setRestaurantName(couponAndRestaurant.getRestaurantName());
			couponAndRestaurantApiDto.setRestaurantAddress(couponAndRestaurant.getRestaurantAddress());
			couponAndRestaurantApiDto.setRestaurantPhoneNumber(couponAndRestaurant.getRestaurantPhoneNumber());
			couponAndRestaurantApiDto.setRestaurantBusinessHours(couponAndRestaurant.getRestaurantBusinessHours());
			couponAndRestaurantApiDto.setRestaurantImageUrl(couponAndRestaurant.getRestaurantImageUrl());
			couponAndRestaurantApiDto.setRestaurantLatitude(couponAndRestaurant.getRestaurantLatitude());
			couponAndRestaurantApiDto.setRestaurantLongitude(couponAndRestaurant.getRestaurantLongitude());

			couponAndRestaurantApiDtoList.add(couponAndRestaurantApiDto);
		}

		return couponAndRestaurantApiDtoList;
	}

	@Override
	public void postCoupons(String deviceId, Long couponId) {

		Customer customer = selectOrCreateCustomer(deviceId);

		CouponHold couponHold = new CouponHold();
		couponHold.setCouponId(couponId);
		couponHold.setCustomerId(customer.getCustomerId());
		couponHold.setGetDatetime(LocalDateTime.now());
		couponHold.setCouponHoldStatus(CouponHoldStatus.ENABLE.getValue());

		couponHoldDao.insert(couponHold);
	}

	@Override
	public void deleteCoupons(String deviceId, Long couponId) {
		// TODO Auto-generated method stub

	}

	@Override
	public LotteryApiDto getLotteries() {

		List<LotteryWithApplicationCount> lotteryWithApplicationCountList = lotteryCustomApiDao.selectCurrentLottery();

		LotteryApiDto lotteryApiDto = null;
		if (!CollectionUtils.isEmpty(lotteryWithApplicationCountList)) {

			LotteryWithApplicationCount current = lotteryWithApplicationCountList.get(0);

			lotteryApiDto = new LotteryApiDto();
			lotteryApiDto.setLotteryId(current.getLotteryId());
			lotteryApiDto.setLotteryTitle(current.getLotteryTitle());
			lotteryApiDto.setLotteryDetail(current.getLotteryDetail());
			lotteryApiDto.setLotteryImageUrl(current.getLotteryImageUrl());
			lotteryApiDto.setEndDatetime(DateTimeFormatter.yyyyMMddHHmm_SLASH_COLON.format(current.getEndDatetime()));
			lotteryApiDto.setAnnouncementDatetime(
					DateTimeFormatter.yyyyMMddHHmm_SLASH_COLON.format(current.getAnnouncementDatetime()));
			lotteryApiDto.setCount(current.getCount());
		}

		return lotteryApiDto;
	}

	@Override
	public void postLotteries(String deviceId, Long couponId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<LotteryApplicationApiDto> getLotteriesHistories(String deviceId) {

		List<LotteryApplication> lotteryApplicationList = lotteryCustomApiDao.selectByDeviceId(deviceId);

		List<LotteryApplicationApiDto> lotteryApplicationApiDtoList = new ArrayList<LotteryApplicationApiDto>();
		for (LotteryApplication lotteryApplication : lotteryApplicationList) {
			LotteryApplicationApiDto lotteryApplicationApiDto = new LotteryApplicationApiDto();
			lotteryApplicationApiDto.setLotteryTitle(lotteryApplication.getLotteryTitle());
			lotteryApplicationApiDto.setLotteryDetail(lotteryApplication.getLotteryDetail());
			lotteryApplicationApiDto.setLotteryApplicationStatus(lotteryApplication.getLotteryTitle());
			lotteryApplicationApiDto.setApplyDatetime(
					DateTimeFormatter.yyyyMMddHHmm_SLASH_COLON.format(lotteryApplication.getApplyDatetime()));

			lotteryApplicationApiDtoList.add(lotteryApplicationApiDto);
		}

		return lotteryApplicationApiDtoList;
	}

	@Override
	public void postFeedbacks(String deviceId, FeedbackApiDto feedbackDto) {

		CustomerDto customerDto = customerService.getByDeviceId(deviceId);

		Feedback feedback = new Feedback();
		feedback.setCustomerId(customerDto.getCustomerId());
		feedback.setType(feedbackDto.getType());
		feedback.setDetail(feedbackDto.getDetail());

		feedbackDao.insert(feedback);

	}

	private Customer selectOrCreateCustomer(String deviceId) {
		Customer customer = customerCustomDao.selectByDeviceId(deviceId);
		if (customer == null) {
			customer = new Customer();
			customer.setDeviceId(deviceId);
			customerDao.insert(customer);
		}
		return customer;
	}
}
