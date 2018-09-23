package jp.co.sparkworks.restaurant.backoffice.dto;

import lombok.Data;

@Data
public class YutaponDto {
	private String id;
	private String deviceId;
    private String yutaponType;
    private String spotName;
    private String dateTime;
    private String status;
}
