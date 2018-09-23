package jp.co.sparkworks.restaurant.backoffice.form;

import lombok.Data;

@Data
public class YutaponSearchForm {

	String dateTimeFrom;//捕獲期間from
	String dateTimeTo;//捕獲期間to
	String spotName;//施設名
	String yutaponType;//ゆたぽんタイプ
	String yutaponStatus;//ステータス
    String deviceId;//端末識別子
}
