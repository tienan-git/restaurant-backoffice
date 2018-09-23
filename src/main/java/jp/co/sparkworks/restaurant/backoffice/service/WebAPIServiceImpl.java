package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sparkworks.restaurant.backoffice.dao.CustomerCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.CustomerDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Customer;
import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;
import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public abstract class WebAPIServiceImpl implements WebAPIService {

    @Autowired
    CustomerDao customerDao;

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

}
