package jp.co.sparkworks.restaurant.backoffice.form;

import lombok.Data;

@Data
public class FeedbackInputForm {
	
	Long feedbackId;
	Long customerId;
	String type;
	String detail;
	String treatmentStatus;
	String treatmentMemo;
	
	
}
