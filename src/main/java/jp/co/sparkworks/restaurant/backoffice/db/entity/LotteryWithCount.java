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
public class LotteryWithCount extends BaseEntity {

    /** 抽選ID */
    @Column(name = "lottery_id")
    Long lotteryId;

    /** 抽選タイトル */
    @Column(name = "lottery_title")
    String lotteryTitle;

    /** 抽選詳細 */
    @Column(name = "lottery_detail")
    String lotteryDetail;

    /** 抽選画像URL */
    @Column(name = "lottery_image_url")
    String lotteryImageUrl;

    /** 応募開始日時 */
    @Column(name = "start_datetime")
    LocalDateTime startDatetime;

    /** 応募終了日時 */
    @Column(name = "end_datetime")
    LocalDateTime endDatetime;

    /** 結果発表日時 */
    @Column(name = "announcement_datetime")
    LocalDateTime announcementDatetime;

    /** クーポンID */
    @Column(name = "coupon_id")
    Long couponId;

    /** 人数 */
    @Column(name = "count")
    int count;
}