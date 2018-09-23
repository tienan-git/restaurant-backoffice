package jp.co.sparkworks.restaurant.backoffice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.sparkworks.restaurant.backoffice.dto.YutaponDto;
import jp.co.sparkworks.restaurant.backoffice.dto.YutaponSearchDto;
import jp.co.sparkworks.restaurant.backoffice.form.YutaponSearchForm;
import jp.co.sparkworks.restaurant.backoffice.service.YutaponService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/yutapon")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class YutaponController {

    @Autowired
    YutaponService yutaponService;

    @GetMapping({ "/list", "/search" })
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.YUTAPON_VIEW + "')")
    public ModelAndView search(ModelAndView mv, @ModelAttribute YutaponSearchForm yutaponSearchForm) {

        log.debug("yutaponSearchForm:{}", yutaponSearchForm);

        YutaponSearchDto yutaponSearchDto = new YutaponSearchDto();
        yutaponSearchDto.setDateTimeFrom(yutaponSearchForm.getDateTimeFrom());
        yutaponSearchDto.setDateTimeTo(yutaponSearchForm.getDateTimeTo());
        yutaponSearchDto.setSpotName(yutaponSearchForm.getSpotName());
        yutaponSearchDto.setYutaponType(yutaponSearchForm.getYutaponType());
        yutaponSearchDto.setStatus(yutaponSearchForm.getYutaponStatus());
        yutaponSearchDto.setDeviceId(yutaponSearchForm.getDeviceId());

        List<YutaponDto> yutaponDtoList = yutaponService.searchAll(yutaponSearchDto);

        mv.addObject("yutaponDtoList", yutaponDtoList);
        mv.setViewName("yutapon/list");
        return mv;
    }

}
