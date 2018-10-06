package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.List;

import jp.co.sparkworks.restaurant.backoffice.dto.FeedbackDto;
import jp.co.sparkworks.restaurant.backoffice.dto.FeedbackSearchDto;

public interface FeedbackService {

	
	List<FeedbackDto> searchAll(FeedbackSearchDto feedbackSearchDto);
	void update(FeedbackDto feedbackDto);
	
	FeedbackDto searchById(Long feedbackId);
}
