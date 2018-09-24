/*
 * Copyright 2018 SparkWorks Co.,Ltd.
 * All rights reserved.
 */

package jp.co.sparkworks.restaurant.api.db.entity;

import java.time.LocalDateTime;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import jp.co.sparkworks.restaurant.backoffice.db.entity.BaseEntity;
import lombok.Data;

/**
 * 
 */
@Entity
@Data
public class LotteryApplication extends BaseEntity {

	@Column(name = "lottery_title")
	String lotteryTitle;
	@Column(name = "lottery_detail")
	String lotteryDetail;
	@Column(name = "lottery_application_status")
	String lotteryApplicationStatus;
	@Column(name = "apply_datetime")
	LocalDateTime applyDatetime;

}