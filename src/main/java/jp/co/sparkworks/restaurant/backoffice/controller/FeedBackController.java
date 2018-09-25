package jp.co.sparkworks.restaurant.backoffice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.sparkworks.restaurant.backoffice.dto.CustomerDto;
import jp.co.sparkworks.restaurant.backoffice.dto.CustomerSearchDto;
import jp.co.sparkworks.restaurant.backoffice.dto.FeedbackDto;
import jp.co.sparkworks.restaurant.backoffice.dto.FeedbackSearchDto;
import jp.co.sparkworks.restaurant.backoffice.form.CustomerSearchForm;
import jp.co.sparkworks.restaurant.backoffice.form.FeedbackSearchForm;
import jp.co.sparkworks.restaurant.backoffice.service.CustomerService;
import jp.co.sparkworks.restaurant.backoffice.service.FeedbackService;
import jp.co.sparkworks.restaurant.backoffice.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/feedback")
public class FeedBackController {

	@Autowired
	FeedbackService feedbackService;

	@GetMapping({ "/list", "/search" })
	// @PreAuthorize("hasAuthority('" +
	// jp.co.opentone.arapp.backoffice.constant.AuthConstant.YUTAPON_VIEW + "')")
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

}
