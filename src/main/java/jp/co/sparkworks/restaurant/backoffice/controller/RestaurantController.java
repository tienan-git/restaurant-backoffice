package jp.co.sparkworks.restaurant.backoffice.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;
import jp.co.sparkworks.restaurant.backoffice.form.RestaurantInputForm;
import jp.co.sparkworks.restaurant.backoffice.service.RestaurantService;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

	@Autowired
	HttpSession session;

	@Autowired
	RestaurantService restaurantService;

	@GetMapping("/list")
	public ModelAndView list() {

		List<RestaurantDto> restaurantDtoList = restaurantService.search();

		ModelAndView mv = new ModelAndView();
		mv.addObject("restaurantDtoList", restaurantDtoList);
		mv.setViewName("restaurant/list");
		return mv;
	}

	@GetMapping("/create")
	public ModelAndView toCreate(RestaurantInputForm restaurantInputForm) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("restaurant/create");
		return mv;
	}

	@PostMapping("/createConfirm")
	public ModelAndView createConfirm(RestaurantInputForm restaurantInputForm) {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("restaurant/createConfirm");
		session.setAttribute("restaurantInputForm", restaurantInputForm);
		return mv;
	}

	@PostMapping("/createComplete")
	public ModelAndView create(@Validated RestaurantInputForm restaurantInputForm, BindingResult result) {

		RestaurantInputForm rtif = (RestaurantInputForm) session.getAttribute("restaurantInputForm");

		RestaurantDto restaurantDto = new RestaurantDto();
		restaurantDto.setRestaurantId(rtif.getRestaurantId());
		restaurantDto.setRestaurantName(rtif.getRestaurantName());
		restaurantDto.setRestaurantManager(rtif.getRestaurantManager());
		restaurantDto.setRestaurantPhone(rtif.getRestaurantPhone());
		restaurantDto.setRestaurantOpenTime(rtif.getRestaurantOpenTime());
		restaurantDto.setRestaurantImageUrl(rtif.getRestaurantImageUrl());
		restaurantDto.setRestaurantUrl(rtif.getRestaurantUrl());
		restaurantDto.setLatitude(rtif.getLatitude());
		restaurantDto.setLongitude(rtif.getLongitude());
		restaurantDto.setRestaurantStatus(rtif.getRestaurantStatus());

		restaurantService.createRestaurant(restaurantDto);

		session.removeAttribute("userInputForm");

		ModelAndView mv = new ModelAndView();
		mv.setViewName("restaurant/createComplete");
		return mv;
	}
}
