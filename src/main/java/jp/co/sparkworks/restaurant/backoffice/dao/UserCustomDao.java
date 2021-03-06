package jp.co.sparkworks.restaurant.backoffice.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;

import jp.co.opentone.arapp.backoffice.db.entity.User;
import jp.co.sparkworks.restaurant.backoffice.db.annotation.InjectConfig;

@Dao
@InjectConfig
public interface UserCustomDao {

    @Select
    User selectByEmail(String email);

    @Select
    List<User> selectAll();

    // 権限リストを取得
    @Select
    List<String> selectByRoleId(String roleId);
}
