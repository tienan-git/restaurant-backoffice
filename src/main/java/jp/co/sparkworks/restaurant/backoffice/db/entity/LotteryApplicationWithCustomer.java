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
import org.seasar.doma.Table;
import org.seasar.doma.Version;

import lombok.Data;

/**
 * 
 */
@Entity
@Data
public class LotteryApplicationWithCustomer extends BaseEntity {

    /** 抽選応募ID */
    @Column(name = "lottery_application_id")
    Long lotteryApplicationId;

    /** 抽選ID */
    @Column(name = "lottery_id")
    Long lotteryId;

    /** 顧客ID */
    @Column(name = "customer_id")
    Long customerId;

    /** 抽選応募ステータス:０：応募済み　１：あたり */
    @Column(name = "lottery_application_status")
    String lotteryApplicationStatus;

    /** 応募日時 */
    @Column(name = "apply_datetime")
    LocalDateTime applyDatetime;

    /** 有効フラグ:０：無効１：有効 */
    @Column(name = "validity_flag")
    String validityFlag;

    /** デバイスID */
    @Column(name = "device_id")
    String deviceId;

    /** ニックネーム */
    @Column(name = "nick_name")
    String nickName;

}