package jp.co.sparkworks.restaurant.backoffice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sparkworks.restaurant.backoffice.dao.CustomerCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.CustomerDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Customer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WebAPIServiceImpl implements WebAPIService {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    CustomerCustomDao customerCustomDao;

    //
    // @Autowired
    // YutaponHistoryCustomDao yutaponHistoryCustomDao;
    //
    @Override
    @Transactional
    public void synchronization(String deviceId, String nickName) {

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

    }

}
