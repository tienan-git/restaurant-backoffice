package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sparkworks.restaurant.backoffice.dao.CustomerCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.CustomerDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Customer;
import jp.co.sparkworks.restaurant.backoffice.dto.CustomerDto;
import jp.co.sparkworks.restaurant.backoffice.dto.CustomerSearchDto;
import jp.co.sparkworks.restaurant.backoffice.enums.DateTimeFormatter;

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
	
	@Transactional
	@Override
	public List<CustomerDto> searchAll(CustomerSearchDto customerSearchDto) {

		 List<Customer> customerList = CustomerCustomDao.selectByCriteria(customerSearchDto);

	        List<CustomerDto> customerDtoList = new ArrayList<CustomerDto>();

	        for (Customer customer : customerList) {

	        	CustomerDto customerDto = new CustomerDto();
	        	customerDto.setCustomerId(customer.getCustomerId());
	        	customerDto.setDeviceId(customer.getDeviceId());
	        	customerDto.setNickName(customer.getNickName());
	        	customerDto.setCreateAt(DateTimeFormatter.yyyyMMddHHmm_SLASH_COLON.format(customer.getCreatedAt()));
	        	
	        	customerDtoList.add(customerDto);

	        }
	        return customerDtoList;



	}

}
