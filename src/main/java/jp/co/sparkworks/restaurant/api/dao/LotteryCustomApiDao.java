package jp.co.sparkworks.restaurant.api.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import jp.co.sparkworks.restaurant.api.db.entity.LotteryApplicationInfo;
import jp.co.sparkworks.restaurant.api.db.entity.LotteryWithApplicationCount;
import jp.co.sparkworks.restaurant.backoffice.db.annotation.InjectConfig;
import jp.co.sparkworks.restaurant.backoffice.db.entity.LotteryApplication;

@Dao
@InjectConfig
public interface LotteryCustomApiDao {

	@Select
	List<LotteryWithApplicationCount> selectCurrentLottery();

	@Select
	List<LotteryApplicationInfo> selectByDeviceId(String deviceId);

	@Select
	LotteryApplication selectByCustomerIdAndLotteryId(Long customerId, Long lotteryId);

}
