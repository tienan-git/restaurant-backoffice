package jp.co.sparkworks.restaurant.backoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sparkworks.restaurant.backoffice.dao.CustomerCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.CustomerDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Customer;
import jp.co.sparkworks.restaurant.backoffice.dto.CustomerDto;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerCustomDao CustomerCustomDao;

	@Autowired
	CustomerDao CustomerDao;

	@Transactional
	@Override
	public CustomerDto getByDeviceId(String deviceId) {

		Customer customer = CustomerCustomDao.selectByDeviceId(deviceId);

		CustomerDto customerDto = new CustomerDto();
		if (customer == null) {
			customer = new Customer();
			customer.setDeviceId(deviceId);
			customer.setNickName(null);
			CustomerDao.insert(customer);

			customerDto.setCustomerId(customer.getCustomerId());
			customerDto.setDeviceId(customer.getDeviceId());

		} else {

			customerDto.setCustomerId(customer.getCustomerId());
			customerDto.setDeviceId(customer.getDeviceId());
		}

		return customerDto;

	}

}
