package jp.co.sparkworks.restaurant.backoffice.dto;

import lombok.Data;

@Data
public class YutaponSearchDto {

	String dateTimeFrom;//捕獲期間from
	String dateTimeTo;//捕獲期間to
	String spotName;//施設名
	String yutaponType;//ゆたぽんタイプ
	String status;//ステータス
    String deviceId;//端末識別子
}
