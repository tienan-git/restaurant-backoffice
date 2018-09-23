package jp.co.sparkworks.restaurant.backoffice.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import jp.co.sparkworks.restaurant.backoffice.db.annotation.InjectConfig;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Coupon;

@Dao
@InjectConfig
public interface CouponCustomDao {


    @Select
    List<Coupon> selectAll();


}
