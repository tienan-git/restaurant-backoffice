package jp.co.sparkworks.restaurant.backoffice.controller;

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
import jp.co.sparkworks.restaurant.backoffice.exception.BusinessException;
import jp.co.sparkworks.restaurant.backoffice.form.LotteryInputForm;
import jp.co.sparkworks.restaurant.backoffice.form.LotterySearchForm;
import jp.co.sparkworks.restaurant.backoffice.service.LotteryService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/lottery")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LotteryController {

    @Autowired
    HttpSession session;

    @Autowired
    LotteryService lotteryService;

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_CREATE + "')")
    public ModelAndView toCreate(ModelAndView mv, LotteryInputForm LotteryInputForm) {
        mv.setViewName("lottery/create");
        return mv;
    }

    @PostMapping("/createConfirm")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_CREATE + "')")
    public ModelAndView createConfirm(ModelAndView mv, LotteryInputForm LotteryInputForm) {
        mv.setViewName("lottery/createConfirm");
        session.setAttribute("lotteryInputForm", LotteryInputForm);
        return mv;
    }

    @PostMapping("/createComplete")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_CREATE + "')")
    public ModelAndView create(ModelAndView mv, @Validated LotteryInputForm LotteryInputForm, BindingResult result) {

        LotteryInputForm uipf = (LotteryInputForm) session.getAttribute("lotteryInputForm");
        BeanUtils.copyProperties(uipf, LotteryInputForm);

        LotteryDto LotteryDto = new LotteryDto();
        LotteryDto.setLotteryId(LotteryInputForm.getLotteryId());
        LotteryDto.setLotteryDetail(LotteryInputForm.getLotteryDetail());
        LotteryDto.setLotteryTitle(LotteryInputForm.getLotteryTitle());
        LotteryDto.setLotteryImageUrl(LotteryInputForm.getLotteryImageUrl());
        LotteryDto.setStartDatetime(LotteryInputForm.getStartDatetime());
        LotteryDto.setEndDatetime(LotteryInputForm.getEndDatetime());
        LotteryDto.setAnnouncementDatetime(LotteryInputForm.getAnnouncementDatetime());
        LotteryDto.setCouponId(LotteryInputForm.getCouponId());
        

        try {
            LotteryDto = lotteryService.create(LotteryDto);
        } catch (jp.co.sparkworks.restaurant.backoffice.exception.BusinessException be) {
            result.reject(be.toString());
            mv.setViewName("lottery/createConfirm");
            mv.addObject("LotteryInputForm", LotteryInputForm);
            return mv;
        }

        session.removeAttribute("lotteryInputForm");

        mv.setViewName("lottery/createComplete");
        mv.addObject("lotteryDto", LotteryDto);
        return mv;
    }

    @GetMapping({"/list","/search"})
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView list(ModelAndView mv, LotterySearchForm lotterySearchForm) {
        
        LotterySearchDto lotterySearchDto = new LotterySearchDto();
	    
        lotterySearchDto.setLotteryId(lotterySearchForm.getLotteryId());

	    List<LotteryDto> lotteryDtoList = lotteryService.searchAll(lotterySearchDto);

		mv.addObject("lotteryDtoList", lotteryDtoList);
		mv.setViewName("lottery/list");

        return mv;
    }

    @PostMapping("/list")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView search() {

        ModelAndView mv = new ModelAndView("lottery/list");
        return mv;
    }

    @GetMapping("/detail")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView detail(@RequestParam int lotteryId, ModelAndView mv) {

        LotteryDto lotteryDto = lotteryService.getById(Long.valueOf(lotteryId));
        
       List< LotteryApplicationDto> lotteryApplicationDtoList = lotteryService.getLotteryApplicationById(Long.valueOf(lotteryId));

        mv.setViewName("lottery/detail");
        
        mv.addObject("lotteryDto", lotteryDto);
        mv.addObject("lotteryApplicationDtoList", lotteryApplicationDtoList);


        return mv;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView toUpdate(@RequestParam Long LotteryId, ModelAndView mv, @Validated LotteryInputForm LotteryInputForm, BindingResult result) {

        LotteryDto LotteryDto = null;
        try {
            LotteryDto = lotteryService.getById(LotteryId);
        } catch (BusinessException be) {
            result.reject(ErrorCodeConstant.E50012);
            mv.setViewName("lottery/list");
         //   List<LotteryDto> LotteryDtoList = lotteryService.search();
            //mv.addObject("lotteryDtoList", LotteryDtoList);
            return mv;
        }

        LotteryInputForm.setLotteryId(LotteryDto.getLotteryId());
        LotteryDto.setLotteryDetail(LotteryInputForm.getLotteryDetail());
        LotteryDto.setLotteryTitle(LotteryInputForm.getLotteryTitle());
        LotteryDto.setLotteryImageUrl(LotteryInputForm.getLotteryImageUrl());
        LotteryDto.setStartDatetime(LotteryInputForm.getStartDatetime());
        LotteryDto.setEndDatetime(LotteryInputForm.getEndDatetime());
        LotteryDto.setAnnouncementDatetime(LotteryInputForm.getAnnouncementDatetime());
        LotteryDto.setCouponId(LotteryInputForm.getCouponId());

        mv.addObject("lotteryInputForm", LotteryInputForm);
        mv.setViewName("lottery/update");
        return mv;

    }

    @PostMapping("/updateConfirm")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView updateConfirm(LotteryInputForm LotteryInputForm) {

        ModelAndView mv = new ModelAndView("lottery/updateConfirm");
        session.setAttribute("lotteryInputForm", LotteryInputForm);
        return mv;

    }

    @PostMapping("/updateComplete")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView update(@Validated LotteryInputForm LotteryInputForm, BindingResult result) {

        LotteryInputForm uif = (LotteryInputForm) session.getAttribute("LotteryInputForm");
        LotteryDto LotteryDto = new LotteryDto();
        BeanUtils.copyProperties(uif, LotteryInputForm);
        LotteryDto.setLotteryId(uif.getLotteryId());
        LotteryDto.setLotteryDetail(LotteryInputForm.getLotteryDetail());
        LotteryDto.setLotteryTitle(LotteryInputForm.getLotteryTitle());
        LotteryDto.setLotteryImageUrl(LotteryInputForm.getLotteryImageUrl());
        LotteryDto.setStartDatetime(LotteryInputForm.getStartDatetime());
        LotteryDto.setEndDatetime(LotteryInputForm.getEndDatetime());
        LotteryDto.setAnnouncementDatetime(LotteryInputForm.getAnnouncementDatetime());
        LotteryDto.setCouponId(LotteryInputForm.getCouponId());
        try {
            lotteryService.update(LotteryDto);
        } catch (BusinessException be) {
            result.reject(ErrorCodeConstant.E50011);
            ModelAndView mv = new ModelAndView("lottery/updateConfirm");
            mv.addObject("lotteryInputForm", LotteryInputForm);
            return mv;
        }

        session.removeAttribute("lotteryInputForm");

        ModelAndView mv = new ModelAndView("lottery/updateComplete");
        mv.addObject("lotteryInputForm", LotteryInputForm);

        return mv;

    }

    @PostMapping("/deleteConfirm")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_DELETE + "')")
    public ModelAndView deleteConfirm(@RequestParam Long LotteryId, ModelAndView mv, @Validated LotteryInputForm LotteryInputForm, BindingResult result) {

        LotteryDto LotteryDto = new LotteryDto();
        try {
            LotteryDto = lotteryService.getById(LotteryId);
        } catch (BusinessException be) {
            result.reject(ErrorCodeConstant.E50012);
            mv.setViewName("lottery/list");
   //         List<LotteryDto> LotteryDtoList = lotteryService.search();
       //     mv.addObject("LotteryDtoList", LotteryDtoList);
            return mv;
        }
        mv.setViewName("lottery/deleteConfirm");
        mv.addObject("lotteryDto", LotteryDto);
        return mv;

    }

    @PostMapping("/deleteComplete")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_DELETE + "')")
    public ModelAndView deleteComplete(@RequestParam Long LotteryId, ModelAndView mv) {

        LotteryDto LotteryDto = lotteryService.getById(LotteryId);
        lotteryService.delete(LotteryId);
        mv.addObject("lotteryDto", LotteryDto);
        mv.setViewName("lottery/deleteComplete");

        return mv;
    }

    @PostMapping("/returnToLotteryList")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView returnToLotteryList(LotteryInputForm LotteryInputForm) {
        ModelAndView mv = new ModelAndView("lottery/list");
  //      List<LotteryDto> LotteryDtoList = lotteryService.search();
      // mv.addObject("lotteryDtoList", LotteryDtoList);
        return mv;
    }

    @PostMapping("/returnToLotteryDetail")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView returnToLotteryDetail(@RequestParam Long LotteryId, ModelAndView mv) {

        LotteryDto LotteryDto = lotteryService.getById(LotteryId);
        mv.setViewName("lottery/detail");
        mv.addObject("lotteryDto", LotteryDto);
        return mv;
    }

    @PostMapping("/returnToUpdate")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView returnToUpdate() {

        LotteryInputForm LotteryInputForm = (LotteryInputForm) session.getAttribute("LotteryInputForm");
        ModelAndView mv = new ModelAndView("lottery/update");
        mv.addObject("lotteryInputForm", LotteryInputForm);
        return mv;

    }

}
