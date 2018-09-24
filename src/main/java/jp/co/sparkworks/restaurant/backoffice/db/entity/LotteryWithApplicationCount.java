/*
 * Copyright 2018 SparkWorks Co.,Ltd.
 * All rights reserved.
 */

package jp.co.sparkworks.restaurant.backoffice.db.entity;

import java.time.LocalDateTime;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Version;

import lombok.Data;

/**
 * 
 */
@Entity
@Data
public class LotteryWithApplicationCount extends BaseEntity {

    /** 抽選ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lottery_id")
    Long lotteryId;

    /** 抽選詳細 */
    @Column(name = "lottery_detail")
    String lotteryDetail;

    /** 抽選タイトル */
    @Column(name = "lottery_title")
    String lotteryTitle;

    /** 抽選画像URL */
    @Column(name = "lottery_image_url")
    String lotteryImageUrl;

    /** 抽選画像 */
    @Column(name = "lottery_image")
    String lotteryImage;

    /** 応募終了日時 */
    @Column(name = "end_datetime")
    LocalDateTime endDatetime;

    /** 結果発表日時 */
    @Column(name = "announcement_datetime")
    LocalDateTime announcementDatetime;
 
    @Column(name = "count")
    int count;
}