package jp.co.sparkworks.restaurant.backoffice.dto;

import lombok.Data;

@Data
public class FeedbackDto {
	
	String customerId;
	String type;
	String detail;
	String treatmentStatus;
	String treatmentMemo;
	
}
