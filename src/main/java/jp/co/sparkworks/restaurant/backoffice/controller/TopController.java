package jp.co.sparkworks.restaurant.backoffice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TopController {

	@GetMapping({ "/", "/index" })
	public String index() {
		return "index";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}
}
