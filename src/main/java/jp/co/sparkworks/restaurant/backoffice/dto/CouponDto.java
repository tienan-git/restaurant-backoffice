package jp.co.sparkworks.restaurant.backoffice.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CouponDto {
    Long couponId;
    String couponname;
    String couponsImageUrl;
    String couponsDetail;
    String couponsPeriod;
    Long restaurantId;
    String name;
    String address;
    String phoneNumber;
    String imageUrl;
    Double Latitude;
    Double Longitude;
    
    String title;
    String detail;
    LocalDate couponStartDate;
    LocalDate couponEndDate;
    Integer couponTotalAmount;
}
