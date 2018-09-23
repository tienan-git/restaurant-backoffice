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
import jp.co.sparkworks.restaurant.backoffice.dto.LotteryEventDto;
import jp.co.sparkworks.restaurant.backoffice.exception.BusinessException;
import jp.co.sparkworks.restaurant.backoffice.form.LotteryEventInputForm;
import jp.co.sparkworks.restaurant.backoffice.service.LotteryEventService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/lotteryEvent")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class LotteryEventController {

    @Autowired
    HttpSession session;

    @Autowired
    LotteryEventService lotteryEventService;

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_CREATE + "')")
    public ModelAndView toCreate(ModelAndView mv, LotteryEventInputForm lotteryEventInputForm) {
        mv.setViewName("lotteryEvent/create");
        return mv;
    }

    @PostMapping("/createConfirm")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_CREATE + "')")
    public ModelAndView createConfirm(ModelAndView mv, LotteryEventInputForm lotteryEventInputForm) {
        mv.setViewName("lotteryEvent/createConfirm");
        session.setAttribute("lotteryEventInputForm", lotteryEventInputForm);
        return mv;
    }

    @PostMapping("/createComplete")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_CREATE + "')")
    public ModelAndView create(ModelAndView mv, @Validated LotteryEventInputForm lotteryEventInputForm, BindingResult result) {

        LotteryEventInputForm uipf = (LotteryEventInputForm) session.getAttribute("lotteryEventInputForm");
        BeanUtils.copyProperties(uipf, lotteryEventInputForm);

        LotteryEventDto lotteryEventDto = new LotteryEventDto();
        lotteryEventDto.setLotteryEventId(lotteryEventInputForm.getLotteryEventId());
        lotteryEventDto.setLotteryDetail(lotteryEventInputForm.getLotteryDetail());
        lotteryEventDto.setLotteryTitle(lotteryEventInputForm.getLotteryTitle());
        lotteryEventDto.setLotteryImageUrl(lotteryEventInputForm.getLotteryImageUrl());
        lotteryEventDto.setStartDatetime(lotteryEventInputForm.getStartDatetime());
        lotteryEventDto.setEndDatetime(lotteryEventInputForm.getEndDatetime());
        lotteryEventDto.setAnnouncementDatetime(lotteryEventInputForm.getAnnouncementDatetime());
        lotteryEventDto.setCouponId(lotteryEventInputForm.getCouponId());
        

        try {
            lotteryEventDto = lotteryEventService.create(lotteryEventDto);
        } catch (jp.co.sparkworks.restaurant.backoffice.exception.BusinessException be) {
            result.reject(be.toString());
            mv.setViewName("lotteryEvent/createConfirm");
            mv.addObject("lotteryEventInputForm", lotteryEventInputForm);
            return mv;
        }

        session.removeAttribute("lotteryEventInputForm");

        mv.setViewName("lotteryEvent/createComplete");
        mv.addObject("lotteryEventDto", lotteryEventDto);
        return mv;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView list(ModelAndView mv, LotteryEventInputForm lotteryEventInputForm) {
        mv.setViewName("lotteryEvent/list");
        List<LotteryEventDto> lotteryEventDtoList = lotteryEventService.search();
        mv.addObject("lotteryEventDtoList", lotteryEventDtoList);

        return mv;
    }

    @PostMapping("/list")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView search() {

        ModelAndView mv = new ModelAndView("lotteryEvent/list");
        return mv;
    }

    @GetMapping("/detail")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView detail(@RequestParam int lotteryEventId, ModelAndView mv) {

        LotteryEventDto lotteryEventDto = lotteryEventService.getById(Long.valueOf(lotteryEventId));

        mv.setViewName("lotteryEvent/detail");
        mv.addObject("lotteryEventDto", lotteryEventDto);

        return mv;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView toUpdate(@RequestParam Long lotteryEventId, ModelAndView mv, @Validated LotteryEventInputForm lotteryEventInputForm, BindingResult result) {

        LotteryEventDto lotteryEventDto = null;
        try {
            lotteryEventDto = lotteryEventService.getById(lotteryEventId);
        } catch (BusinessException be) {
            result.reject(ErrorCodeConstant.E50012);
            mv.setViewName("lotteryEvent/list");
            List<LotteryEventDto> lotteryEventDtoList = lotteryEventService.search();
            mv.addObject("lotteryEventDtoList", lotteryEventDtoList);
            return mv;
        }

        lotteryEventInputForm.setLotteryEventId(lotteryEventDto.getLotteryEventId());
        lotteryEventDto.setLotteryDetail(lotteryEventInputForm.getLotteryDetail());
        lotteryEventDto.setLotteryTitle(lotteryEventInputForm.getLotteryTitle());
        lotteryEventDto.setLotteryImageUrl(lotteryEventInputForm.getLotteryImageUrl());
        lotteryEventDto.setStartDatetime(lotteryEventInputForm.getStartDatetime());
        lotteryEventDto.setEndDatetime(lotteryEventInputForm.getEndDatetime());
        lotteryEventDto.setAnnouncementDatetime(lotteryEventInputForm.getAnnouncementDatetime());
        lotteryEventDto.setCouponId(lotteryEventInputForm.getCouponId());

        mv.addObject("lotteryEventInputForm", lotteryEventInputForm);
        mv.setViewName("lotteryEvent/update");
        return mv;

    }

    @PostMapping("/updateConfirm")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView updateConfirm(LotteryEventInputForm lotteryEventInputForm) {

        ModelAndView mv = new ModelAndView("lotteryEvent/updateConfirm");
        session.setAttribute("lotteryEventInputForm", lotteryEventInputForm);
        return mv;

    }

    @PostMapping("/updateComplete")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView update(@Validated LotteryEventInputForm lotteryEventInputForm, BindingResult result) {

        LotteryEventInputForm uif = (LotteryEventInputForm) session.getAttribute("lotteryEventInputForm");
        LotteryEventDto lotteryEventDto = new LotteryEventDto();
        BeanUtils.copyProperties(uif, lotteryEventInputForm);
        lotteryEventDto.setLotteryEventId(uif.getLotteryEventId());
        lotteryEventDto.setLotteryDetail(lotteryEventInputForm.getLotteryDetail());
        lotteryEventDto.setLotteryTitle(lotteryEventInputForm.getLotteryTitle());
        lotteryEventDto.setLotteryImageUrl(lotteryEventInputForm.getLotteryImageUrl());
        lotteryEventDto.setStartDatetime(lotteryEventInputForm.getStartDatetime());
        lotteryEventDto.setEndDatetime(lotteryEventInputForm.getEndDatetime());
        lotteryEventDto.setAnnouncementDatetime(lotteryEventInputForm.getAnnouncementDatetime());
        lotteryEventDto.setCouponId(lotteryEventInputForm.getCouponId());
        try {
            lotteryEventService.update(lotteryEventDto);
        } catch (BusinessException be) {
            result.reject(ErrorCodeConstant.E50011);
            ModelAndView mv = new ModelAndView("lotteryEvent/updateConfirm");
            mv.addObject("lotteryEventInputForm", lotteryEventInputForm);
            return mv;
        }

        session.removeAttribute("lotteryEventInputForm");

        ModelAndView mv = new ModelAndView("lotteryEvent/updateComplete");
        mv.addObject("lotteryEventInputForm", lotteryEventInputForm);

        return mv;

    }

    @PostMapping("/deleteConfirm")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_DELETE + "')")
    public ModelAndView deleteConfirm(@RequestParam Long lotteryEventId, ModelAndView mv, @Validated LotteryEventInputForm lotteryEventInputForm, BindingResult result) {

        LotteryEventDto lotteryEventDto = new LotteryEventDto();
        try {
            lotteryEventDto = lotteryEventService.getById(lotteryEventId);
        } catch (BusinessException be) {
            result.reject(ErrorCodeConstant.E50012);
            mv.setViewName("lotteryEvent/list");
            List<LotteryEventDto> lotteryEventDtoList = lotteryEventService.search();
            mv.addObject("lotteryEventDtoList", lotteryEventDtoList);
            return mv;
        }
        mv.setViewName("lotteryEvent/deleteConfirm");
        mv.addObject("lotteryEventDto", lotteryEventDto);
        return mv;

    }

    @PostMapping("/deleteComplete")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_DELETE + "')")
    public ModelAndView deleteComplete(@RequestParam Long lotteryEventId, ModelAndView mv) {

        LotteryEventDto lotteryEventDto = lotteryEventService.getById(lotteryEventId);
        lotteryEventService.delete(lotteryEventId);
        mv.addObject("lotteryEventDto", lotteryEventDto);
        mv.setViewName("lotteryEvent/deleteComplete");

        return mv;
    }

    @PostMapping("/returnToLotteryEventList")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView returnToLotteryEventList(LotteryEventInputForm lotteryEventInputForm) {
        ModelAndView mv = new ModelAndView("lotteryEvent/list");
        List<LotteryEventDto> lotteryEventDtoList = lotteryEventService.search();
        mv.addObject("lotteryEventDtoList", lotteryEventDtoList);
        return mv;
    }

    @PostMapping("/returnToLotteryEventDetail")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView returnToLotteryEventDetail(@RequestParam Long lotteryEventId, ModelAndView mv) {

        LotteryEventDto lotteryEventDto = lotteryEventService.getById(lotteryEventId);
        mv.setViewName("lotteryEvent/detail");
        mv.addObject("lotteryEventDto", lotteryEventDto);
        return mv;
    }

    @PostMapping("/returnToUpdate")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView returnToUpdate() {

        LotteryEventInputForm lotteryEventInputForm = (LotteryEventInputForm) session.getAttribute("lotteryEventInputForm");
        ModelAndView mv = new ModelAndView("lotteryEvent/update");
        mv.addObject("lotteryEventInputForm", lotteryEventInputForm);
        return mv;

    }

}
