package jp.co.sparkworks.restaurant.api.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import jp.co.sparkworks.restaurant.api.db.entity.CouponAndRestaurant;
import jp.co.sparkworks.restaurant.backoffice.db.annotation.InjectConfig;
import jp.co.sparkworks.restaurant.backoffice.db.entity.CouponHold;

@Dao
@InjectConfig
public interface CouponCustomApiDao {

	@Select
	List<CouponAndRestaurant> selectByDeviceId(String deviceId);

	@Select
	CouponHold selectByCustomerIdAndCouponId(Long customerId, Long couponId);

}
