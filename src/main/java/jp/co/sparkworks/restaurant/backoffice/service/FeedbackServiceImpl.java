package jp.co.sparkworks.restaurant.backoffice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sparkworks.restaurant.backoffice.dao.FeedbackCustomDao;
import jp.co.sparkworks.restaurant.backoffice.db.dao.FeedbackDao;
import jp.co.sparkworks.restaurant.backoffice.db.entity.Feedback;
import jp.co.sparkworks.restaurant.backoffice.dto.FeedbackDto;
import jp.co.sparkworks.restaurant.backoffice.dto.FeedbackSearchDto;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	FeedbackCustomDao feedbackCustomDao;

	@Autowired
	FeedbackDao feedbackDao;


	
@Transactional
@Override
	public List<FeedbackDto> searchAll(FeedbackSearchDto feedbackSearchDto) {

		 List<Feedback> feedbackList = feedbackCustomDao.selectByCriteria(feedbackSearchDto);

	        List<FeedbackDto> feedbackDtoList = new ArrayList<FeedbackDto>();

	        for (Feedback feedback : feedbackList) {

	        	FeedbackDto feedbackDto = new FeedbackDto();
	        	feedbackDto.setCustomerId(feedback.getCustomerId().toString());
	        	feedbackDto.setType(feedback.getType());
	        	feedbackDto.setDetail(feedback.getDetail());
	        	feedbackDto.setTreatmentStatus(feedback.getTreatmentStatus());
	        	feedbackDto.setTreatmentMemo(feedback.getTreatmentMemo());
	        	
	        	feedbackDtoList.add(feedbackDto);

	        }
	        return feedbackDtoList;



	}

	

}
