package jp.co.sparkworks.restaurant.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sparkworks.restaurant.api.dto.FeedbackApiDto;
import jp.co.sparkworks.restaurant.backoffice.dao.CustomerCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.CustomerDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.FeedbackDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Customer;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Feedback;
import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;
import jp.co.sparkworks.restaurant.backoffice.dto.CustomerDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryDto;
import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;
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
	CustomerService customerService;

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
	public LotteryDto getLotteries() {
		// TODO Auto-generated method stub
		return null;
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
