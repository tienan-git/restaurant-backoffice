package jp.co.sparkworks.restaurant.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import jp.co.sparkworks.restaurant.api.dao.LotteryCustomApiDao;
import jp.co.sparkworks.restaurant.api.dto.CouponApiDto;
import jp.co.sparkworks.restaurant.api.dto.FeedbackApiDto;
import jp.co.sparkworks.restaurant.api.dto.LotteryApiDto;
import jp.co.sparkworks.restaurant.api.dto.RestaurantApiDto;
import jp.co.sparkworks.restaurant.backoffice.dao.CustomerCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.CustomerDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.FeedbackDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Customer;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Feedback;
import jp.co.sparkworks.restaurant.backoffice.db.entity.LotteryWithApplicationCount;
import jp.co.sparkworks.restaurant.backoffice.dto.CustomerDto;
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

	@Override
	@Transactional
	public List<CouponApiDto> synchronization(String deviceId, String nickName) {

		// まず、ニックネーム設定
		Customer customer = customerCustomDao.selectByDeviceId(deviceId);
		if (customer == null) {
			Customer newCustomer = new Customer();
			newCustomer.setDeviceId(deviceId);
			newCustomer.setNickName(nickName);
			customerDao.insert(newCustomer);

		} else {
			customer.setNickName(nickName);
			customerDao.update(customer);
		}

		// あと、クーポン情報返す
		List<CouponApiDto> couponDtoList = new ArrayList<CouponApiDto>();
		return couponDtoList;
	}

	@Override
	public List<RestaurantApiDto> getRestaurants() {

		return null;
	}

	@Override
	public void postCoupons(String deviceId, String couponId) {

	}

	@Override
	public void deleteCoupons(String deviceId, String couponId) {
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
	public void postLotteries(String deviceId, String couponId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List getLotteriesHistories(String deviceId) {
		// TODO Auto-generated method stub
		return null;
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

}
