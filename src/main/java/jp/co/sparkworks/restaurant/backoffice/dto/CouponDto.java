package jp.co.sparkworks.restaurant.backoffice.dto;

import lombok.Data;

@Data
public class CouponDto {
    Long couponId;
    String couponname;
    String couponsImageUrl;
    String couponsDetail;
    String couponsPeriod;
    String restaurantId;
    String name;
    String address;
    String phoneNumber;
    String imageUrl;
    Double Latitude;
    Double Longitude;
}
