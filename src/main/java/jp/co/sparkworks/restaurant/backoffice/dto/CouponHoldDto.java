package jp.co.sparkworks.restaurant.backoffice.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.seasar.doma.Column;
import org.seasar.doma.Version;

import lombok.Data;

@Data
public class CouponHoldDto {
	Long couponId;
	Long restaurantId;
	String title;
	String detail;
	LocalDate startDate;
	LocalDate endDate;
	Integer totalAmount;
	int count;

}
