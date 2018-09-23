package jp.co.sparkworks.restaurant.backoffice.form;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CouponInputForm {
	Long couponId;
	Long restaurantId;
	String title;
    String detail;
    LocalDate couponStartDate;
    LocalDate couponEndDate;
    Integer couponTotalAmount;
}
