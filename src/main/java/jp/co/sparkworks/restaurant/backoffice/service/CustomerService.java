package jp.co.sparkworks.restaurant.backoffice.service;

import jp.co.sparkworks.restaurant.backoffice.dto.CustomerDto;

public interface CustomerService {

	CustomerDto getByDeviceId(String deviceId);

}
