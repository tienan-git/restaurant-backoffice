package jp.co.sparkworks.restaurant.backoffice.form;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CouponInputForm {

	/** クーポン保有ID */
	Long couponHoldId;

	/** クーポンID */
	Long couponId;

	/** 顧客ID */
	Long customerId;

	/** 取得日時 */
	LocalDateTime getDatetime;

	/** クーポン保有ステータス:０：追加済み １：削除（利用）済み */
	String couponHoldStatus;
}
