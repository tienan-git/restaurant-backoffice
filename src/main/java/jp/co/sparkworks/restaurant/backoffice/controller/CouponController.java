package jp.co.sparkworks.restaurant.backoffice.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;
import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;
import jp.co.sparkworks.restaurant.backoffice.form.CouponInputForm;
import jp.co.sparkworks.restaurant.backoffice.form.RestaurantInputForm;
import jp.co.sparkworks.restaurant.backoffice.service.CouponService;

@Controller
@RequestMapping("/coupon")
public class CouponController {

	@Autowired
	HttpSession session;

	@Autowired
	CouponService couponService;

	@GetMapping("/eventList")
	public ModelAndView eventList() {

		List<CouponDto> couponDtoList = couponService.getEventList();

		ModelAndView mv = new ModelAndView();
		mv.addObject("couponDtoList", couponDtoList);
		mv.setViewName("coupon/eventList");
		return mv;
	}

	@GetMapping("/createCoupon")
	public ModelAndView toCreate(CouponInputForm couponInputForm) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("coupon/createCoupon");
		return mv;
	}

	@PostMapping("/createCouponConfirm")
	public ModelAndView createConfirm(CouponInputForm couponInputForm) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("coupon/createCouponConfirm");
		session.setAttribute("couponInputForm", couponInputForm);
		return mv;
	}

	@PostMapping("/createCouponComplete")
	public ModelAndView create(@Validated CouponInputForm couponInputForm, BindingResult result) {

		CouponInputForm ctif = (CouponInputForm) session.getAttribute("couponInputForm");

		CouponDto couponDto = new CouponDto();
		couponDto.setCouponId(ctif.getCouponId());
		

		session.removeAttribute("userInputForm");

		ModelAndView mv = new ModelAndView();
		mv.setViewName("restaurant/createComplete");
		return mv;
	}
}
