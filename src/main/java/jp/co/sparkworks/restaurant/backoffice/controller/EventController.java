package jp.co.sparkworks.restaurant.backoffice.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.co.sparkworks.restaurant.backoffice.constant.ErrorCodeConstant;
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryApplicationDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotterySearchDto;
import jp.co.sparkworks.restaurant.backoffice.form.EventSearchForm;
import jp.co.sparkworks.restaurant.backoffice.form.LotteryInputForm;
import jp.co.sparkworks.restaurant.backoffice.form.LotterySearchForm;
import jp.co.sparkworks.restaurant.backoffice.service.LotteryService;
import jp.co.sparkworks.restaurant.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/event")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class EventController {

    @Autowired
    HttpSession session;

    @Autowired
    LotteryService lotteryService;

    @GetMapping({"/eventApplicantionList","/search"})
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView list(ModelAndView mv, EventSearchForm eventSearchForm) {
        
//        LotterySearchDto lotterySearchDto = new LotterySearchDto();
//	    
//        lotterySearchDto.setLotteryId(lotterySearchForm.getLotteryId());
//
//	    List<LotteryDto> lotteryDtoList = lotteryService.searchAll(lotterySearchDto);
//
//		mv.addObject("lotteryDtoList", lotteryDtoList);
//		mv.setViewName("event/eventApplicantionList");

        return mv;
    }


}
