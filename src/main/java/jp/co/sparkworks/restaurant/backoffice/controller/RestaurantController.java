package jp.co.sparkworks.restaurant.backoffice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.sparkworks.restaurant.backoffice.dto.RestaurantDto;
import jp.co.sparkworks.restaurant.backoffice.service.RestaurantService;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {

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
}
