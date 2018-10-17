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
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryBingoDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryDto;
import jp.co.sparkworks.restaurant.backoffice.dto.LotterySearchDto;
import jp.co.sparkworks.restaurant.backoffice.form.LotteryBingoForm;
import jp.co.sparkworks.restaurant.backoffice.form.LotteryInputForm;
import jp.co.sparkworks.restaurant.backoffice.form.LotterySearchForm;
import jp.co.sparkworks.restaurant.backoffice.service.LotteryService;
import jp.co.sparkworks.restaurant.exception.BusinessException;

@Controller
@RequestMapping("/lottery")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LotteryController {

	@Autowired
	HttpSession session;

	@Autowired
	LotteryService lotteryService;

	@GetMapping("/create")
	@PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.LOTTERY_CREATE + "')")
	public ModelAndView toCreate(ModelAndView mv, LotteryInputForm lotteryInputForm) {
		mv.setViewName("lottery/create");
		return mv;
	}

	@PostMapping("/createConfirm")
	@PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.LOTTERY_CREATE + "')")
	public ModelAndView createConfirm(ModelAndView mv, LotteryInputForm lotteryInputForm) {
		mv.setViewName("lottery/createConfirm");
		session.setAttribute("lotteryInputForm", lotteryInputForm);
		return mv;
	}

	@PostMapping("/createComplete")
	@PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_CREATE + "')")
	public ModelAndView create(ModelAndView mv, @Validated LotteryInputForm lotteryInputForm, BindingResult result) {

		LotteryInputForm uipf = (LotteryInputForm) session.getAttribute("lotteryInputForm");
		BeanUtils.copyProperties(uipf, lotteryInputForm);

		LotteryDto lotteryDto = new LotteryDto();
		lotteryDto.setLotteryId(lotteryInputForm.getLotteryId());
		lotteryDto.setLotteryDetail(lotteryInputForm.getLotteryDetail());
		lotteryDto.setLotteryTitle(lotteryInputForm.getLotteryTitle());
		lotteryDto.setLotteryImageUrl(lotteryInputForm.getLotteryImageUrl());
		lotteryDto.setStartDatetime(LocalDateTime.parse(lotteryInputForm.getStartDatetime()));
		lotteryDto.setEndDatetime(LocalDateTime.parse(lotteryInputForm.getEndDatetime()));
		lotteryDto.setAnnouncementDatetime(LocalDateTime.parse(lotteryInputForm.getAnnouncementDatetime()));
		lotteryDto.setDisplayStartDatetime(LocalDateTime.parse(lotteryInputForm.getDisplayStartDatetime()));
		lotteryDto.setDisplayEndDatetime(LocalDateTime.parse(lotteryInputForm.getDisplayEndDatetime()));
		lotteryDto.setLotteryStatus(lotteryInputForm.getLotteryStatus());
	//	lotteryDto.setCouponId(lotteryInputForm.getCouponId());

		try {
			lotteryDto = lotteryService.create(lotteryDto);
		} catch (BusinessException be) {
			result.reject(be.toString());
			mv.setViewName("lottery/createConfirm");
			mv.addObject("lotteryInputForm", lotteryInputForm);
			return mv;
		}

		session.removeAttribute("lotteryInputForm");

		mv.setViewName("lottery/createComplete");
		mv.addObject("lotteryDto", lotteryDto);
		return mv;
	}

	@GetMapping({ "/list", "/search" })
	@PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.LOTTERY_VIEW + "')")
	public ModelAndView list(ModelAndView mv, LotterySearchForm lotterySearchForm) {

		LotterySearchDto lotterySearchDto = new LotterySearchDto();

		lotterySearchDto.setLotteryId(lotterySearchForm.getLotteryId());

		List<LotteryDto> lotteryDtoList = lotteryService.searchAll(lotterySearchDto);

		mv.addObject("lotteryDtoList", lotteryDtoList);
		mv.setViewName("lottery/list");

		return mv;
	}

	@GetMapping("/detail")
	@PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.LOTTERY_VIEW + "')")
	public ModelAndView detail(@RequestParam int lotteryId, ModelAndView mv) {

		LotteryDto lotteryDto = lotteryService.getById(Long.valueOf(lotteryId));

		List<LotteryApplicationDto> lotteryApplicationDtoList = lotteryService.getLotteryApplicationById(Long.valueOf(lotteryId));

		mv.setViewName("lottery/detail");

		mv.addObject("lotteryDto", lotteryDto);
		mv.addObject("lotteryApplicationDtoList", lotteryApplicationDtoList);
		mv.addObject("lotteryBingoForm", new LotteryBingoForm());

		return mv;
	}

	@PostMapping("/bingo")
	public ModelAndView bingo(@RequestParam int lotteryId, LotteryBingoForm lotteryBingoForm) {

		LotteryBingoDto lotteryBingoDto = new LotteryBingoDto();
		lotteryBingoDto.setLotteryId(lotteryBingoForm.getLotteryId());
		lotteryBingoDto.setIds(lotteryBingoForm.getIds());
		lotteryService.bingo(lotteryBingoDto);
		ModelAndView mv = new ModelAndView("/lottery/detail");
		return detail(lotteryBingoForm.getLotteryId().intValue(), mv);
	}

	@PostMapping("/update")
	@PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.LOTTERY_UPDATE + "')")
	public ModelAndView toUpdate(@RequestParam Long lotteryId, ModelAndView mv, @Validated LotteryInputForm lotteryInputForm, BindingResult result) {

		LotteryDto LotteryDto = null;
		try {
			LotteryDto = lotteryService.getById(lotteryId);
		} catch (BusinessException be) {
			result.reject(ErrorCodeConstant.E50012);
			mv.setViewName("lottery/list");
			// List<LotteryDto> LotteryDtoList = lotteryService.search();
			// mv.addObject("lotteryDtoList", LotteryDtoList);
			return mv;
		}

		lotteryInputForm.setLotteryId(LotteryDto.getLotteryId());
		lotteryInputForm.setLotteryDetail(LotteryDto.getLotteryDetail());
		lotteryInputForm.setLotteryTitle(LotteryDto.getLotteryTitle());
		lotteryInputForm.setLotteryImageUrl(LotteryDto.getLotteryImageUrl());
		lotteryInputForm.setStartDatetime(LotteryDto.getStartDatetime().toString());
		lotteryInputForm.setEndDatetime(LotteryDto.getEndDatetime().toString());
		lotteryInputForm.setAnnouncementDatetime(LotteryDto.getAnnouncementDatetime().toString());
		lotteryInputForm.setDisplayStartDatetime(LotteryDto.getDisplayStartDatetime().toString());
		lotteryInputForm.setDisplayEndDatetime(LotteryDto.getDisplayEndDatetime().toString());
		//lotteryInputForm.setCouponId(LotteryDto.getCouponId());

		mv.addObject("lotteryInputForm", lotteryInputForm);
		mv.setViewName("lottery/update");
		return mv;

	}

	@PostMapping("/updateConfirm")
	@PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.LOTTERY_UPDATE + "')")
	public ModelAndView updateConfirm(LotteryInputForm lotteryInputForm) {

		ModelAndView mv = new ModelAndView("lottery/updateConfirm");
		session.setAttribute("lotteryInputForm", lotteryInputForm);
		return mv;

	}

	@PostMapping("/updateComplete")
	@PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.LOTTERY_UPDATE + "')")
	public ModelAndView updateComplete(@Validated LotteryInputForm lotteryInputForm, BindingResult result) {

		LotteryInputForm uif = (LotteryInputForm) session.getAttribute("lotteryInputForm");
		LotteryDto lotteryDto = new LotteryDto();
		BeanUtils.copyProperties(uif, lotteryInputForm);
		lotteryDto.setLotteryId(uif.getLotteryId());
		lotteryDto.setLotteryDetail(lotteryInputForm.getLotteryDetail());
		lotteryDto.setLotteryTitle(lotteryInputForm.getLotteryTitle());
		lotteryDto.setLotteryImageUrl(lotteryInputForm.getLotteryImageUrl());
		lotteryDto.setStartDatetime(LocalDateTime.parse(lotteryInputForm.getStartDatetime()));
		lotteryDto.setEndDatetime(LocalDateTime.parse(lotteryInputForm.getEndDatetime()));
		lotteryDto.setAnnouncementDatetime(LocalDateTime.parse(lotteryInputForm.getAnnouncementDatetime()));
		lotteryDto.setDisplayStartDatetime(LocalDateTime.parse(lotteryInputForm.getDisplayStartDatetime()));
		lotteryDto.setDisplayEndDatetime(LocalDateTime.parse(lotteryInputForm.getDisplayEndDatetime()));
		//lotteryDto.setCouponId(lotteryInputForm.getCouponId());
		try {
			lotteryService.update(lotteryDto);
		} catch (BusinessException be) {
			result.reject(ErrorCodeConstant.E50011);
			ModelAndView mv = new ModelAndView("lottery/updateConfirm");
			mv.addObject("lotteryInputForm", lotteryInputForm);
			return mv;
		}

		session.removeAttribute("lotteryInputForm");

		ModelAndView mv = new ModelAndView("lottery/updateComplete");
		mv.addObject("lotteryInputForm", lotteryInputForm);

		return mv;

	}

	@PostMapping("/deleteConfirm")
	@PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.LOTTERY_DELETE + "')")
	public ModelAndView deleteConfirm(@RequestParam Long lotteryId, ModelAndView mv, @Validated LotteryInputForm lotteryInputForm, BindingResult result) {

		LotteryDto lotteryDto = new LotteryDto();
		try {
			lotteryDto = lotteryService.getById(lotteryId);
		} catch (BusinessException be) {
			result.reject(ErrorCodeConstant.E50012);
			mv.setViewName("lottery/list");
			// List<LotteryDto> lotteryDtoList = lotteryService.search();
			// mv.addObject("lotteryDtoList", lotteryDtoList);
			return mv;
		}
		mv.setViewName("lottery/deleteConfirm");
		mv.addObject("lotteryDto", lotteryDto);
		return mv;

	}

	@PostMapping("/deleteComplete")
	@PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.LOTTERY_DELETE + "')")
	public ModelAndView deleteComplete(@RequestParam Long lotteryId, ModelAndView mv) {

		LotteryDto lotteryDto = lotteryService.getById(lotteryId);
		lotteryService.delete(lotteryId);
		mv.addObject("lotteryDto", lotteryDto);
		mv.setViewName("lottery/deleteComplete");

		return mv;
	}

	

	@PostMapping("/returnToLotteryDetail")
	@PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.LOTTERY_VIEW + "')")
	public ModelAndView returnToLotteryDetail(@RequestParam Long lotteryId, ModelAndView mv) {

		LotteryDto LotteryDto = lotteryService.getById(lotteryId);
		mv.setViewName("lottery/detail");
		mv.addObject("lotteryDto", LotteryDto);
		return mv;
	}

	@PostMapping("/returnToUpdate")
	@PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.LOTTERY_UPDATE + "')")
	public ModelAndView returnToUpdate() {

		LotteryInputForm lotteryInputForm = (LotteryInputForm) session.getAttribute("lotteryInputForm");
		ModelAndView mv = new ModelAndView("lottery/update");
		mv.addObject("lotteryInputForm", lotteryInputForm);
		return mv;

	}

}
