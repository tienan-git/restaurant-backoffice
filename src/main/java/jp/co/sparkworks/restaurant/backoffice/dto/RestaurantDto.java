package jp.co.sparkworks.restaurant.backoffice.dto;

import lombok.Data;

@Data
public class RestaurantDto {

	/** 店舗ID */
	Long restaurantId;

	/** 店舗名 */
	String restaurantName;

	/** 店舗担当者 */
	String restaurantManager;

	/** 店舗連絡先 */
	String restaurantPhone;

	/** 店舗営業時間 */
	String restaurantOpenTime;

	/** 店舗URL */
	String restaurantUrl;

	/** 店舗画像URL */
	String restaurantImageUrl;

	/** 緯度 */
	Float latitude;

	/** 経度 */
	Float longitude;

	/** 店舗ステータス */
	String restaurantStatus;

	/** 店舗アドレス */
	String restaurantAddress;

	/** 店舗備考 */
	String restaurantMemo;
}