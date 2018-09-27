/*
 * Copyright 2018 SparkWorks Co.,Ltd.
 * All rights reserved.
 */

package jp.co.sparkworks.restaurant.backoffice.db.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.Version;

import lombok.Data;

/**
 * 
 */
@Entity
@Data
public class CouponWithCount extends BaseEntity {

    /** クーポンID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    Long couponId;

    /** 店舗ID */
    @Column(name = "restaurant_id")
    Long restaurantId;

    /** タイトル */
    @Column(name = "title")
    String title;

    /** 詳細 */
    @Column(name = "detail")
    String detail;

    /** 有効開始日 */
    @Column(name = "start_date")
    LocalDate startDate;

    /** 有効終了日 */
    @Column(name = "end_date")
    LocalDate endDate;

    /** クーポン枚数 */
    @Column(name = "total_amount")
    Integer totalAmount;
    
    /** 人数 */
    @Column(name = "count")
    int count;
    
    /** バージョン番号 */
    @Version
    @Column(name = "version_no")
    Long versionNo;

   
}