package jp.co.sparkworks.restaurant.backoffice.dto;

import lombok.Data;

@Data
public class FeedbackSearchDto {
	String customerId;
	String deviceId;
	String type;
	String treatmentStatus;
	
}
