package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sparkworks.restaurant.backoffice.dao.CustomerCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.CustomerDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.FeedbackDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Customer;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Feedback;
import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;
import jp.co.sparkworks.restaurant.backoffice.dto.FeedbackDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryEventDto;
import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;
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

	@Override
	@Transactional
	public List<CouponDto> synchronization(String deviceId, String nickName) {

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
		List<CouponDto> couponDtoList = new ArrayList<CouponDto>();
		return couponDtoList;
	}

	@Override
	public List<RestaurantDto> getRestaurants() {

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
	public LotteryEventDto getCoupons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postCouponsDeviceId(String deviceId, String couponId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List getCouponsHistories(String deviceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postFeedbacks(String deviceId, FeedbackDto feedbackDto) {

		Feedback feedback = new Feedback();
		feedback.setDeviceId(deviceId);
		feedback.setType(feedbackDto.getType());
		feedback.setDetail(feedbackDto.getDetail());

		feedbackDao.insert(feedback);

	}

}
