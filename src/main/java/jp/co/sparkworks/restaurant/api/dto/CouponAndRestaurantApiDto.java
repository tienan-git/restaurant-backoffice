package jp.co.sparkworks.restaurant.api.dto;

import lombok.Data;

@Data
public class CouponAndRestaurantApiDto {
	Long couponId;
	String couponTitle;
	String couponsDetail;
	String couponsEndDate;
	Long restaurantId;
	String restaurantName;
	String restaurantAddress;
	String restaurantPhoneNumber;
	String restaurantBusinessHours;
	String restaurantImageUrl;
	String restaurantSiteUrl;
	Double restaurantLatitude;
	Double restaurantLongitude;
}
