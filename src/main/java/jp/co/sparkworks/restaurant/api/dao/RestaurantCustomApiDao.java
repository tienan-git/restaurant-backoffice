package jp.co.sparkworks.restaurant.api.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import jp.co.sparkworks.restaurant.api.db.entity.CouponAndRestaurant;
import jp.co.sparkworks.restaurant.backoffice.db.annotation.InjectConfig;

@Dao
@InjectConfig
public interface RestaurantCustomApiDao {

	@Select
	List<CouponAndRestaurant> selectAll();

}
