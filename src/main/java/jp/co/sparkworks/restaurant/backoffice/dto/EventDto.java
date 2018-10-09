package jp.co.sparkworks.restaurant.backoffice.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EventDto {
	
	Long restaurantId;
	
	String title;
	String detail;
	LocalDateTime startDatetime;
	String courseDetail;
	Integer budget;
	Integer applicantUpperLimit;
	Integer applicantLowerLimit;
	Long customerId;
	String eventApplicationStatus;
	LocalDateTime applyDatetime;
	String validityFlag;
	
}
