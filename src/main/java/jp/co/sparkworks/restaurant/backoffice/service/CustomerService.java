package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.CustomerDto;
import jp.co.sparkworks.restaurant.backoffice.dto.CustomerFavoriteDto;
import jp.co.sparkworks.restaurant.backoffice.dto.CustomerSearchDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryApplicationDto;
import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;

public interface CustomerService {
	List<CustomerDto> searchAll(CustomerSearchDto customerSearchDto);
	CustomerDto getById(Long customerId);
	List<CustomerFavoriteDto> getCustomerFavoriteById(Long customerId);
}
