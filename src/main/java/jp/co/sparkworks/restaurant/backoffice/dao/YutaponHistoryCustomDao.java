/*
 * Copyright 2018 SparkWorks Co.,Ltd.
 * All rights reserved.
 */

package jp.co.sparkworks.restaurant.backoffice.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.jdbc.SelectOptions;

import jp.co.opentone.arapp.backoffice.db.entity.YutaponHistory;
import jp.co.sparkworks.restaurant.backoffice.db.annotation.InjectConfig;
import jp.co.sparkworks.restaurant.backoffice.dto.YutaponSearchDto;

/**
 */
@Dao
@InjectConfig
public interface YutaponHistoryCustomDao {

    @Select
    List<YutaponHistory> selectByDeviceId(String deviceId);

    @Select
    List<YutaponHistory> selectByCriteria(YutaponSearchDto yutaponSearchDto, SelectOptions selectOptions);

}