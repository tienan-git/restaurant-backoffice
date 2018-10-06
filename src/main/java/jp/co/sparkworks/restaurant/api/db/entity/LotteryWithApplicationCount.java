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
public class LotteryWithApplicationCount extends BaseEntity {

	//@Column(name = "device_id")
	//Long deviceId;
	
	@Column(name = "lottery_id")
	Long lotteryId;

	@Column(name = "lottery_detail")
	String lotteryDetail;

	@Column(name = "lottery_title")
	String lotteryTitle;

	@Column(name = "lottery_image_url")
	String lotteryImageUrl;

	@Column(name = "lottery_image")
	String lotteryImage;

	@Column(name = "end_datetime")
	LocalDateTime endDatetime;

	@Column(name = "announcement_datetime")
	LocalDateTime announcementDatetime;

	@Column(name = "count")
	int count;
	
	@Column(name = "lottery_application_status")
	String lotteryApplicationStatus;
}