package jp.co.sparkworks.restaurant.api.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import jp.co.sparkworks.restaurant.api.db.entity.CouponAndRestaurant;
import jp.co.sparkworks.restaurant.backoffice.db.annotation.InjectConfig;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Favorite;

@Dao
@InjectConfig
public interface FavoriteRestaurantApiDao {

	@Select
	List<CouponAndRestaurant> selectByCustomerId(long customerId);

	@Select
	Favorite selectByCustomerIdAndRestaurantId(long customerId, long restaurantId);

}
