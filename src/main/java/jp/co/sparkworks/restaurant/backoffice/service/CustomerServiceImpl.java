package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sparkworks.restaurant.backoffice.dao.CustomerCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.CustomerDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Customer;
import jp.co.sparkworks.restaurant.backoffice.db.entity.CustomerFavorite;
import jp.co.sparkworks.restaurant.backoffice.db.entity.LotteryApplicationWithCustomer;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Restaurant;
import jp.co.sparkworks.restaurant.backoffice.dto.CustomerDto;
import jp.co.sparkworks.restaurant.backoffice.dto.CustomerFavoriteDto;
import jp.co.sparkworks.restaurant.backoffice.dto.CustomerSearchDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryApplicationDto;
import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;
import jp.co.sparkworks.restaurant.backoffice.enums.DateTimeFormatter;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerCustomDao CustomerCustomDao;

    @Autowired
    CustomerDao customerDao;

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
    
	@Override
	public CustomerDto getById(Long customerId) {

		Customer customer = customerDao.selectById(customerId);
		if (customer == null) {
		}
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerId(customer.getCustomerId());
		customerDto.setDeviceId(customer.getDeviceId());
		 customerDto.setCreateAt(DateTimeFormatter.yyyyMMddHHmm_SLASH_COLON.format(customer.getCreatedAt()));
		customerDto.setNickName(customer.getNickName());

		return customerDto;
	}
	@Override
	  
	public List<CustomerFavoriteDto> getCustomerFavoriteById(Long customerId) {

		List<CustomerFavorite> favoriteList = CustomerCustomDao. selectFavoriteByCustomerId(customerId);

		List<CustomerFavoriteDto> customerFavoriteDtoList = new ArrayList<CustomerFavoriteDto>();

		for (CustomerFavorite favorite : favoriteList) {

			CustomerFavoriteDto customerFavoriteDto = new CustomerFavoriteDto();
			customerFavoriteDto.setRestaurantId(favorite.getRestaurantId());
			customerFavoriteDto.setRestaurantName(favorite.getRestaurantName());
			customerFavoriteDto.setCreateDatetime(favorite.getCreateDatetime());
		
		

			customerFavoriteDtoList.add(customerFavoriteDto);

			// TODO Auto-generated method stub

		}
		return customerFavoriteDtoList;
	}


}

