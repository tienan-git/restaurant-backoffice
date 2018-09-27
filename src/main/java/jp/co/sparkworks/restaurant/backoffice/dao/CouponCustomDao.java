package jp.co.sparkworks.restaurant.backoffice.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import jp.co.sparkworks.restaurant.backoffice.db.annotation.InjectConfig;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Coupon;
import jp.co.sparkworks.restaurant.backoffice.db.entity.CouponWithCount;
import jp.co.sparkworks.restaurant.backoffice.db.entity.LotteryWithCount;
import jp.co.sparkworks.restaurant.backoffice.dto.CouponSearchDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotterySearchDto;

@Dao
@InjectConfig
public interface CouponCustomDao {

	@Select
	List<CouponWithCount> selectByCriteria(CouponSearchDto couponSearchDto);

}
