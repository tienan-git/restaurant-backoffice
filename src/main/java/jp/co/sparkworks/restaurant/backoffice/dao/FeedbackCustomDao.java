package jp.co.sparkworks.restaurant.backoffice.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import jp.co.sparkworks.restaurant.backoffice.db.annotation.InjectConfig;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Feedback;
import jp.co.sparkworks.restaurant.backoffice.dto.FeedbackSearchDto;

@Dao
@InjectConfig
public interface FeedbackCustomDao {

    @Select
    Feedback selectByDeviceId(String deviceId);
    
    @Select
    List<Feedback> selectByCriteria(FeedbackSearchDto feedbackSearchDto);
}
