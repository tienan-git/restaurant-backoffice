package jp.co.sparkworks.restaurant.backoffice.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import jp.co.sparkworks.restaurant.backoffice.db.annotation.InjectConfig;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Customer;
import jp.co.sparkworks.restaurant.backoffice.db.entity.CustomerFavorite;
import jp.co.sparkworks.restaurant.backoffice.db.entity.LotteryApplicationWithCustomer;
import jp.co.sparkworks.restaurant.backoffice.dto.CustomerSearchDto;

@Dao
@InjectConfig
public interface CustomerCustomDao {

    @Select
    Customer selectByDeviceId(String deviceId);
    
    @Select
    List<Customer> selectByCriteria(CustomerSearchDto customerSearchDto);
    
    @Select
    List<CustomerFavorite> selectFavoriteByCustomerId(Long customerId);
}
