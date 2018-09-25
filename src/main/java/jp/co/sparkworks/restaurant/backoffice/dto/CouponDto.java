package jp.co.sparkworks.restaurant.backoffice.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CouponDto {

	/** クーポンID */
	Long couponId;

	/** 店舗ID */
	Long restaurantId;

	/** タイトル */
	String title;

	/** 詳細 */
	String detail;

	/** 有効開始日 */
	LocalDate startDate;

	/** 有効終了日 */
	LocalDate endDate;

	/** クーポン枚数 */
	Integer totalAmount;
}
