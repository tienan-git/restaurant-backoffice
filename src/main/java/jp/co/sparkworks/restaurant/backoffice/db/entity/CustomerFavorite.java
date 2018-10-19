/*
 * Copyright 2018 SparkWorks Co.,Ltd.
 * All rights reserved.
 */

package jp.co.sparkworks.restaurant.backoffice.db.entity;

import java.time.LocalDateTime;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import lombok.Data;

/**
 * 
 */
@Entity
@Data
public class CustomerFavorite extends BaseEntity {

    /** 店舗ID */
    @Column(name = "restaurant_id")
    Long restaurantId;

    /** 店舗名 */
    @Column(name = "restaurant_name")
    String restaurantName;

    /** 作成日時 */
    @Column(name = "create_datetime")
    String createDatetime;
}
 