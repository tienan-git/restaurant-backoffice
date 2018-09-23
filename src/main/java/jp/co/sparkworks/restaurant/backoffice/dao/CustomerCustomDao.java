package jp.co.sparkworks.restaurant.backoffice.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import jp.co.sparkworks.restaurant.backoffice.db.annotation.InjectConfig;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Customer;

@Dao
@InjectConfig
public interface CustomerCustomDao {

    @Select
    Customer selectByDeviceId(String deviceId);

}
