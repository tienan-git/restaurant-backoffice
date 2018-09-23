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
import jp.co.sparkworks.restaurant.backoffice.dto.CouponDto;
import jp.co.sparkworks.restaurant.backoffice.exception.BusinessException;
import jp.co.sparkworks.restaurant.backoffice.form.CouponInputForm;
import jp.co.sparkworks.restaurant.backoffice.service.CouponService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/coupon")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CouponController {

    @Autowired
    HttpSession session;

    @Autowired
    CouponService couponService;

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_CREATE + "')")
    public ModelAndView toCreate(ModelAndView mv, CouponInputForm couponInputForm) {
        mv.setViewName("coupon/create");
        return mv;
    }

    @PostMapping("/createConfirm")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_CREATE + "')")
    public ModelAndView createConfirm(ModelAndView mv, CouponInputForm couponInputForm) {
        mv.setViewName("coupon/createConfirm");
        session.setAttribute("couponInputForm", couponInputForm);
        return mv;
    }

    @PostMapping("/createComplete")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_CREATE + "')")
    public ModelAndView create(ModelAndView mv, @Validated CouponInputForm couponInputForm, BindingResult result) {

        CouponInputForm uipf = (CouponInputForm) session.getAttribute("couponInputForm");
        BeanUtils.copyProperties(uipf, couponInputForm);

        CouponDto couponDto = new CouponDto();

        couponDto.setCouponId(couponInputForm.getCouponId());
        couponDto.setRestaurantId(couponInputForm.getRestaurantId());
        couponDto.setTitle(couponInputForm.getTitle());
        couponDto.setDetail(couponInputForm.getDetail());
        couponDto.setCouponStartDate(couponInputForm.getCouponStartDate());
        couponDto.setCouponEndDate(couponInputForm.getCouponEndDate());
        couponDto.setCouponTotalAmount(couponInputForm.getCouponTotalAmount());
        
        try {
            couponDto = couponService.create(couponDto);
        } catch (jp.co.sparkworks.restaurant.backoffice.exception.BusinessException be) {
            result.reject(be.toString());
            mv.setViewName("coupon/createConfirm");
            mv.addObject("couponInputForm", couponInputForm);
            return mv;
        }

        session.removeAttribute("couponInputForm");

        mv.setViewName("coupon/createComplete");
        mv.addObject("couponDto", couponDto);
        return mv;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView list(ModelAndView mv, CouponInputForm couponInputForm) {
        mv.setViewName("coupon/list");
        List<CouponDto> couponDtoList = couponService.search();
        mv.addObject("couponDtoList", couponDtoList);

        return mv;
    }

    @PostMapping("/list")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView search() {

        ModelAndView mv = new ModelAndView("coupon/list");
        return mv;
    }

    @GetMapping("/detail")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView detail(@RequestParam int couponId, ModelAndView mv) {

        CouponDto couponDto = couponService.getById(Long.valueOf(couponId));

        mv.setViewName("coupon/detail");
        mv.addObject("couponDto", couponDto);

        return mv;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView toUpdate(@RequestParam Long couponId, ModelAndView mv, @Validated CouponInputForm couponInputForm, BindingResult result) {

        CouponDto couponDto = null;
        try {
            couponDto = couponService.getById(couponId);
        } catch (BusinessException be) {
            result.reject(ErrorCodeConstant.E50012);
            mv.setViewName("coupon/list");
            List<CouponDto> couponDtoList = couponService.search();
            mv.addObject("couponDtoList", couponDtoList);
            return mv;
        }

        couponInputForm.setCouponId(couponDto.getCouponId());
        couponInputForm.setRestaurantId(couponDto.getRestaurantId());
        couponInputForm.setTitle(couponDto.getTitle());
        couponInputForm.setDetail(couponDto.getDetail());
        couponInputForm.setCouponStartDate(couponDto.getCouponStartDate());
        couponInputForm.setCouponEndDate(couponDto.getCouponEndDate());
        couponInputForm.setCouponTotalAmount(couponDto.getCouponTotalAmount());
        
        
        mv.addObject("couponInputForm", couponInputForm);
        mv.setViewName("coupon/update");
        return mv;

    }

    @PostMapping("/updateConfirm")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView updateConfirm(CouponInputForm couponInputForm) {

        ModelAndView mv = new ModelAndView("coupon/updateConfirm");
        session.setAttribute("couponInputForm", couponInputForm);
        return mv;

    }

    @PostMapping("/updateComplete")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView update(@Validated CouponInputForm couponInputForm, BindingResult result) {

        CouponInputForm uif = (CouponInputForm) session.getAttribute("couponInputForm");
        CouponDto couponDto = new CouponDto();
        BeanUtils.copyProperties(uif, couponInputForm);

        couponDto.setCouponId(uif.getCouponId());
        couponDto.setRestaurantId(uif.getRestaurantId());
        couponDto.setTitle(uif.getTitle());
        couponDto.setDetail(uif.getDetail());
        couponDto.setCouponStartDate(uif.getCouponStartDate());
        couponDto.setCouponEndDate(uif.getCouponEndDate());
        couponDto.setCouponTotalAmount(uif.getCouponTotalAmount());
        
        try {
            couponService.update(couponDto);
        } catch (BusinessException be) {
            result.reject(ErrorCodeConstant.E50011);
            ModelAndView mv = new ModelAndView("coupon/updateConfirm");
            mv.addObject("couponInputForm", couponInputForm);
            return mv;
        }

        session.removeAttribute("couponInputForm");

        ModelAndView mv = new ModelAndView("coupon/updateComplete");
        mv.addObject("couponInputForm", couponInputForm);

        return mv;

    }

    @PostMapping("/deleteConfirm")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_DELETE + "')")
    public ModelAndView deleteConfirm(@RequestParam Long couponId, ModelAndView mv, @Validated CouponInputForm couponInputForm, BindingResult result) {

        CouponDto couponDto = new CouponDto();
        try {
            couponDto = couponService.getById(couponId);
        } catch (BusinessException be) {
            result.reject(ErrorCodeConstant.E50012);
            mv.setViewName("coupon/list");
            List<CouponDto> couponDtoList = couponService.search();
            mv.addObject("couponDtoList", couponDtoList);
            return mv;
        }
        mv.setViewName("coupon/deleteConfirm");
        mv.addObject("couponDto", couponDto);
        return mv;

    }

    @PostMapping("/deleteComplete")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_DELETE + "')")
    public ModelAndView deleteComplete(@RequestParam Long couponId, ModelAndView mv) {

        CouponDto couponDto = couponService.getById(couponId);
        couponService.delete(couponId);
        mv.addObject("couponDto", couponDto);
        mv.setViewName("coupon/deleteComplete");

        return mv;
    }

    @PostMapping("/returnToCouponList")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView returnToCouponList(CouponInputForm couponInputForm) {
        ModelAndView mv = new ModelAndView("coupon/list");
        List<CouponDto> couponDtoList = couponService.search();
        mv.addObject("couponDtoList", couponDtoList);
        return mv;
    }

    @PostMapping("/returnToCouponDetail")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView returnToCouponDetail(@RequestParam Long couponId, ModelAndView mv) {

        CouponDto couponDto = couponService.getById(couponId);
        mv.setViewName("coupon/detail");
        mv.addObject("couponDto", couponDto);
        return mv;
    }

    @PostMapping("/returnToUpdate")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView returnToUpdate() {

        CouponInputForm couponInputForm = (CouponInputForm) session.getAttribute("couponInputForm");
        ModelAndView mv = new ModelAndView("coupon/update");
        mv.addObject("couponInputForm", couponInputForm);
        return mv;

    }

}
