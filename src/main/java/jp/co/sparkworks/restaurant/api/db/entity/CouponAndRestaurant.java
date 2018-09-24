/*
 * Copyright 2018 SparkWorks Co.,Ltd.
 * All rights reserved.
 */

package jp.co.sparkworks.restaurant.api.db.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import jp.co.sparkworks.restaurant.backoffice.db.entity.BaseEntity;
import lombok.Data;

/**
 * 
 */
@Entity
@Data
public class CouponAndRestaurant extends BaseEntity {

	@Column(name = "coupon_id")
	Long couponId;
	@Column(name = "title")
	String couponTitle;
	@Column(name = "detail")
	String couponsDetail;
	@Column(name = "end_date")
	String couponsEndDate;

	@Column(name = "restaurant_id")
	Long restaurantId;
	@Column(name = "restaurant_name")
	String restaurantName;
	@Column(name = "address")
	String restaurantAddress;
	@Column(name = "telephone_phone")
	String restaurantPhoneNumber;
	@Column(name = "business_hours")
	String restaurantBusinessHours;
	@Column(name = "image_url")
	String restaurantImageUrl;
	@Column(name = "site_url")
	String restaurantSiteUrl;
	@Column(name = "latitude")
	Double restaurantLatitude;
	@Column(name = "longitude")
	Double restaurantLongitude;

}