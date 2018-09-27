package jp.co.sparkworks.restaurant.backoffice.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;
import jp.co.sparkworks.restaurant.backoffice.dto.CouponHoldDto;
import jp.co.sparkworks.restaurant.backoffice.dto.CouponSearchDto;
import jp.co.sparkworks.restaurant.backoffice.form.CouponInputForm;
import jp.co.sparkworks.restaurant.backoffice.form.CouponSearchForm;
import jp.co.sparkworks.restaurant.backoffice.service.CouponService;

@Controller
@RequestMapping("/coupon")
public class CouponController {

	@Autowired
	HttpSession session;

	@Autowired
	CouponService couponService;

	@GetMapping({"/list","/search"})
	 @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
	
	public ModelAndView List(ModelAndView mv, CouponSearchForm couponSearchForm) {
	
         CouponSearchDto couponSearchDto = new CouponSearchDto();
	    
	        couponSearchDto.setCouponId(couponSearchForm.getCouponId());

		    List<CouponHoldDto> couponDtoList = couponService.searchAll(couponSearchDto);
		    
     	mv.addObject("couponDtoList", couponDtoList);
         mv.setViewName("coupon/list");
         return mv;
		
		
	}

	@GetMapping("/create")
	public ModelAndView toCreate(CouponInputForm couponInputForm) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("coupon/create");
		return mv;
	}

	@PostMapping("/createConfirm")
	public ModelAndView createConfirm(CouponInputForm couponInputForm) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("coupon/createConfirm");
		session.setAttribute("couponInputForm", couponInputForm);
		return mv;
	}

	@PostMapping("/createComplete")
	public ModelAndView create(@Validated CouponInputForm couponInputForm, BindingResult result) {

		CouponInputForm ctif = (CouponInputForm) session.getAttribute("couponInputForm");

		CouponDto couponDto = new CouponDto();
		couponDto.setCouponId(ctif.getCouponId());

		session.removeAttribute("couponInputForm");

		ModelAndView mv = new ModelAndView();
		mv.setViewName("coupon/createComplete");
		return mv;
	}
}
