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
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryDto;
import jp.co.sparkworks.restaurant.backoffice.exception.BusinessException;
import jp.co.sparkworks.restaurant.backoffice.form.LotteryEventInputForm;
import jp.co.sparkworks.restaurant.backoffice.form.LotteryEventInputForm;
import jp.co.sparkworks.restaurant.backoffice.service.LotteryService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/Lottery")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LotteryController {

    @Autowired
    HttpSession session;

    @Autowired
    LotteryService LotteryService;

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_CREATE + "')")
    public ModelAndView toCreate(ModelAndView mv, LotteryEventInputForm LotteryEventInputForm) {
        mv.setViewName("Lottery/create");
        return mv;
    }

    @PostMapping("/createConfirm")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_CREATE + "')")
    public ModelAndView createConfirm(ModelAndView mv, LotteryEventInputForm LotteryEventInputForm) {
        mv.setViewName("Lottery/createConfirm");
        session.setAttribute("LotteryEventInputForm", LotteryEventInputForm);
        return mv;
    }

    @PostMapping("/createComplete")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_CREATE + "')")
    public ModelAndView create(ModelAndView mv, @Validated LotteryEventInputForm LotteryEventInputForm, BindingResult result) {

        LotteryEventInputForm uipf = (LotteryEventInputForm) session.getAttribute("LotteryEventInputForm");
        BeanUtils.copyProperties(uipf, LotteryEventInputForm);

        LotteryDto LotteryDto = new LotteryDto();
        LotteryDto.setLotteryId(LotteryEventInputForm.getLotteryEventId());
        LotteryDto.setLotteryDetail(LotteryEventInputForm.getLotteryDetail());
        LotteryDto.setLotteryTitle(LotteryEventInputForm.getLotteryTitle());
        LotteryDto.setLotteryImageUrl(LotteryEventInputForm.getLotteryImageUrl());
        LotteryDto.setStartDatetime(LotteryEventInputForm.getStartDatetime());
        LotteryDto.setEndDatetime(LotteryEventInputForm.getEndDatetime());
        LotteryDto.setAnnouncementDatetime(LotteryEventInputForm.getAnnouncementDatetime());
        LotteryDto.setCouponId(LotteryEventInputForm.getCouponId());
        

        try {
            LotteryDto = LotteryService.create(LotteryDto);
        } catch (jp.co.sparkworks.restaurant.backoffice.exception.BusinessException be) {
            result.reject(be.toString());
            mv.setViewName("Lottery/createConfirm");
            mv.addObject("LotteryEventInputForm", LotteryEventInputForm);
            return mv;
        }

        session.removeAttribute("LotteryEventInputForm");

        mv.setViewName("Lottery/createComplete");
        mv.addObject("LotteryDto", LotteryDto);
        return mv;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView list(ModelAndView mv, LotteryEventInputForm LotteryEventInputForm) {
        mv.setViewName("Lottery/list");
        List<LotteryDto> LotteryDtoList = LotteryService.search();
        mv.addObject("LotteryDtoList", LotteryDtoList);

        return mv;
    }

    @PostMapping("/list")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView search() {

        ModelAndView mv = new ModelAndView("Lottery/list");
        return mv;
    }

    @GetMapping("/detail")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView detail(@RequestParam int LotteryId, ModelAndView mv) {

        LotteryDto LotteryDto = LotteryService.getById(Long.valueOf(LotteryId));

        mv.setViewName("Lottery/detail");
        mv.addObject("LotteryDto", LotteryDto);

        return mv;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView toUpdate(@RequestParam Long LotteryId, ModelAndView mv, @Validated LotteryEventInputForm LotteryEventInputForm, BindingResult result) {

        LotteryDto LotteryDto = null;
        try {
            LotteryDto = LotteryService.getById(LotteryId);
        } catch (BusinessException be) {
            result.reject(ErrorCodeConstant.E50012);
            mv.setViewName("Lottery/list");
            List<LotteryDto> LotteryDtoList = LotteryService.search();
            mv.addObject("LotteryDtoList", LotteryDtoList);
            return mv;
        }

        LotteryEventInputForm.setLotteryEventId(LotteryDto.getLotteryId());
        LotteryDto.setLotteryDetail(LotteryEventInputForm.getLotteryDetail());
        LotteryDto.setLotteryTitle(LotteryEventInputForm.getLotteryTitle());
        LotteryDto.setLotteryImageUrl(LotteryEventInputForm.getLotteryImageUrl());
        LotteryDto.setStartDatetime(LotteryEventInputForm.getStartDatetime());
        LotteryDto.setEndDatetime(LotteryEventInputForm.getEndDatetime());
        LotteryDto.setAnnouncementDatetime(LotteryEventInputForm.getAnnouncementDatetime());
        LotteryDto.setCouponId(LotteryEventInputForm.getCouponId());

        mv.addObject("LotteryEventInputForm", LotteryEventInputForm);
        mv.setViewName("Lottery/update");
        return mv;

    }

    @PostMapping("/updateConfirm")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView updateConfirm(LotteryEventInputForm LotteryEventInputForm) {

        ModelAndView mv = new ModelAndView("Lottery/updateConfirm");
        session.setAttribute("LotteryEventInputForm", LotteryEventInputForm);
        return mv;

    }

    @PostMapping("/updateComplete")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView update(@Validated LotteryEventInputForm LotteryEventInputForm, BindingResult result) {

        LotteryEventInputForm uif = (LotteryEventInputForm) session.getAttribute("LotteryEventInputForm");
        LotteryDto LotteryDto = new LotteryDto();
        BeanUtils.copyProperties(uif, LotteryEventInputForm);
        LotteryDto.setLotteryId(uif.getLotteryEventId());
        LotteryDto.setLotteryDetail(LotteryEventInputForm.getLotteryDetail());
        LotteryDto.setLotteryTitle(LotteryEventInputForm.getLotteryTitle());
        LotteryDto.setLotteryImageUrl(LotteryEventInputForm.getLotteryImageUrl());
        LotteryDto.setStartDatetime(LotteryEventInputForm.getStartDatetime());
        LotteryDto.setEndDatetime(LotteryEventInputForm.getEndDatetime());
        LotteryDto.setAnnouncementDatetime(LotteryEventInputForm.getAnnouncementDatetime());
        LotteryDto.setCouponId(LotteryEventInputForm.getCouponId());
        try {
            LotteryService.update(LotteryDto);
        } catch (BusinessException be) {
            result.reject(ErrorCodeConstant.E50011);
            ModelAndView mv = new ModelAndView("Lottery/updateConfirm");
            mv.addObject("LotteryEventInputForm", LotteryEventInputForm);
            return mv;
        }

        session.removeAttribute("LotteryEventInputForm");

        ModelAndView mv = new ModelAndView("Lottery/updateComplete");
        mv.addObject("LotteryEventInputForm", LotteryEventInputForm);

        return mv;

    }

    @PostMapping("/deleteConfirm")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_DELETE + "')")
    public ModelAndView deleteConfirm(@RequestParam Long LotteryId, ModelAndView mv, @Validated LotteryEventInputForm LotteryEventInputForm, BindingResult result) {

        LotteryDto LotteryDto = new LotteryDto();
        try {
            LotteryDto = LotteryService.getById(LotteryId);
        } catch (BusinessException be) {
            result.reject(ErrorCodeConstant.E50012);
            mv.setViewName("Lottery/list");
            List<LotteryDto> LotteryDtoList = LotteryService.search();
            mv.addObject("LotteryDtoList", LotteryDtoList);
            return mv;
        }
        mv.setViewName("Lottery/deleteConfirm");
        mv.addObject("LotteryDto", LotteryDto);
        return mv;

    }

    @PostMapping("/deleteComplete")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_DELETE + "')")
    public ModelAndView deleteComplete(@RequestParam Long LotteryId, ModelAndView mv) {

        LotteryDto LotteryDto = LotteryService.getById(LotteryId);
        LotteryService.delete(LotteryId);
        mv.addObject("LotteryDto", LotteryDto);
        mv.setViewName("Lottery/deleteComplete");

        return mv;
    }

    @PostMapping("/returnToLotteryList")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView returnToLotteryList(LotteryEventInputForm LotteryEventInputForm) {
        ModelAndView mv = new ModelAndView("Lottery/list");
        List<LotteryDto> LotteryDtoList = LotteryService.search();
        mv.addObject("LotteryDtoList", LotteryDtoList);
        return mv;
    }

    @PostMapping("/returnToLotteryDetail")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView returnToLotteryDetail(@RequestParam Long LotteryId, ModelAndView mv) {

        LotteryDto LotteryDto = LotteryService.getById(LotteryId);
        mv.setViewName("Lottery/detail");
        mv.addObject("LotteryDto", LotteryDto);
        return mv;
    }

    @PostMapping("/returnToUpdate")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView returnToUpdate() {

        LotteryEventInputForm LotteryEventInputForm = (LotteryEventInputForm) session.getAttribute("LotteryEventInputForm");
        ModelAndView mv = new ModelAndView("Lottery/update");
        mv.addObject("LotteryEventInputForm", LotteryEventInputForm);
        return mv;

    }

}
