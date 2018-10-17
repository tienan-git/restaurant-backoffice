package jp.co.sparkworks.restaurant.backoffice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.sparkworks.restaurant.backoffice.dto.CustomerDto;
import jp.co.sparkworks.restaurant.backoffice.dto.CustomerSearchDto;
import jp.co.sparkworks.restaurant.backoffice.form.CustomerSearchForm;
import jp.co.sparkworks.restaurant.backoffice.service.CustomerService;
import jp.co.sparkworks.restaurant.backoffice.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping({ "/list", "/search" })
	@PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.CUSTOMER_VIEW + "')")
	public ModelAndView search(ModelAndView mv, @ModelAttribute CustomerSearchForm customerSearchForm) {

		log.debug("customerSearchForm:{}", customerSearchForm);

	    CustomerSearchDto customerSearchDto = new CustomerSearchDto();
	    
	    customerSearchDto.setDeviceId(customerSearchForm.getDeviceId());
	    customerSearchDto.setNickName(customerSearchForm.getNickName());

		List<CustomerDto> customerDtoList = customerService.searchAll(customerSearchDto);

		mv.addObject("customerDtoList", customerDtoList);
		mv.setViewName("customer/list");
		return mv;
	}

}
