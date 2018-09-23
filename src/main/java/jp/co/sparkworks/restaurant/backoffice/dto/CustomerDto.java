package jp.co.sparkworks.restaurant.backoffice.dto;

import lombok.Data;

@Data
public class CustomerDto {
	Long customerId;
	String deviceId;
	String nickName;
}
