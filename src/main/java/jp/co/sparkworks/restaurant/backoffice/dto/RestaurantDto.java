package jp.co.sparkworks.restaurant.backoffice.dto;

import lombok.Data;

@Data
public class RestaurantDto {

	/** 店舗ID */
	Long restaurantId;

	/** 店舗名 */
	String restaurantName;

	/** 店舗担当者 */
	String manager;

	/** 店舗連絡先 */
	String telephonePhone;

	/** 店舗営業時間 */
	String businessHours;

	/** 店舗URL */
	String siteUrl;

	/** 店舗画像URL */
	String imageUrl;

	/** 緯度 */
	Double latitude;

	/** 経度 */
	Double longitude;

	/** 店舗ステータス */
	String status;

	/** 店舗アドレス */
	String address;

	/** 店舗備考 */
	String memo;
}
