package jp.co.sparkworks.restaurant.backoffice.dto;

import lombok.Data;

@Data
public class FeedbackDto {
	
	Long feedbackId;
	Long customerId;
	String type;
	String detail;
	String treatmentStatus;
	String treatmentMemo;
	
}
