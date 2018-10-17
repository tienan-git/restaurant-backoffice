package jp.co.sparkworks.restaurant.backoffice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.sparkworks.restaurant.backoffice.dto.FeedbackDto;
import jp.co.sparkworks.restaurant.backoffice.dto.FeedbackSearchDto;
import jp.co.sparkworks.restaurant.backoffice.form.FeedbackInputForm;
import jp.co.sparkworks.restaurant.backoffice.form.FeedbackSearchForm;
import jp.co.sparkworks.restaurant.backoffice.service.FeedbackService;
import jp.co.sparkworks.restaurant.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/feedback")
public class FeedBackController {

	@Autowired
	FeedbackService feedbackService;

	@GetMapping({ "/list", "/search" })
	@PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.FEEDBACK_VIEW + "')")
	public ModelAndView search(ModelAndView mv, @ModelAttribute FeedbackSearchForm feedbackSearchForm) {

		log.debug("feedbackSearchForm:{}", feedbackSearchForm);

         FeedbackSearchDto feedbackSearchDto = new FeedbackSearchDto();
	    
         feedbackSearchDto.setDeviceId(feedbackSearchForm.getDeviceId());
         feedbackSearchDto.setType(feedbackSearchForm.getType());
         feedbackSearchDto.setTreatmentStatus(feedbackSearchForm.getTreatmentStatus());
       
		List<FeedbackDto> feedbackDtoList = feedbackService.searchAll(feedbackSearchDto);

		mv.addObject("feedbackDtoList", feedbackDtoList);
		mv.setViewName("feedback/list");
		return mv;
	}
	
	@GetMapping("/update")
	@PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.FEEDBACK_UPDATE + "')")
	public ModelAndView update(Long feedbackId, ModelAndView mv,
			BindingResult result) {

		FeedbackDto feedbackDto = feedbackService.searchById(feedbackId);
		
		
		mv.addObject("feedbackDto", feedbackDto);
		mv.setViewName("feedback/update");
		return mv;
	}

	@PostMapping("/updateComplete")
	@PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.FEEDBACK_UPDATE + "')")
	public ModelAndView updateComplete(@Validated FeedbackInputForm feedbackInputForm, BindingResult result, ModelAndView mv) {

		FeedbackDto feedbackDto = new FeedbackDto();
		feedbackDto.setFeedbackId(feedbackInputForm.getFeedbackId());
		feedbackDto.setCustomerId(feedbackInputForm.getCustomerId());
		feedbackDto.setDetail(feedbackInputForm.getDetail());
		feedbackDto.setTreatmentMemo(feedbackInputForm.getTreatmentMemo());
		feedbackDto.setTreatmentStatus(feedbackInputForm.getTreatmentStatus());
		feedbackDto.setType(feedbackInputForm.getType());
		try {
			feedbackService.update(feedbackDto);
		}catch(BusinessException be){
			result.reject(be.getMessage());

			mv.setViewName("feedback/update");
			mv.addObject("feedbackInputForm",feedbackInputForm);
			return mv;
		}
		
		mv.addObject("feedbackInputForm", feedbackInputForm);
		mv.setViewName("feedback/updateComplete");
		return mv;
	}

}
