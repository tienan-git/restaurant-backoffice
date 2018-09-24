package jp.co.sparkworks.restaurant.api.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import jp.co.sparkworks.restaurant.backoffice.db.annotation.InjectConfig;
import jp.co.sparkworks.restaurant.backoffice.db.entity.LotteryWithApplicationCount;

@Dao
@InjectConfig
public interface LotteryCustomApiDao {


    @Select
    List<LotteryWithApplicationCount> selectCurrentLottery();


}
