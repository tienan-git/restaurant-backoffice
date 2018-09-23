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
import jp.co.sparkworks.restaurant.backoffice.dto.UserDto;
import jp.co.sparkworks.restaurant.backoffice.exception.BusinessException;
import jp.co.sparkworks.restaurant.backoffice.form.UserInputForm;
import jp.co.sparkworks.restaurant.backoffice.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserController {

    @Autowired
    HttpSession session;

    @Autowired
    UserService userService;

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_CREATE + "')")
    public ModelAndView toCreate(ModelAndView mv, UserInputForm userInputForm) {
        mv.setViewName("user/create");
        return mv;
    }

    @PostMapping("/createConfirm")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_CREATE + "')")
    public ModelAndView createConfirm(ModelAndView mv, UserInputForm userInputForm) {
        mv.setViewName("user/createConfirm");
        session.setAttribute("userInputForm", userInputForm);
        return mv;
    }

    @PostMapping("/createComplete")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_CREATE + "')")
    public ModelAndView create(ModelAndView mv, @Validated UserInputForm userInputForm, BindingResult result) {

        UserInputForm uipf = (UserInputForm) session.getAttribute("userInputForm");
        BeanUtils.copyProperties(uipf, userInputForm);

        UserDto userDto = new UserDto();
        userDto.setUserId(userInputForm.getUserId());
        userDto.setUserName(userInputForm.getUserName());
        userDto.setEmail(userInputForm.getEmail());
        userDto.setRoleId(userInputForm.getRoleId());
        userDto.setPassword(userInputForm.getPassword());

        try {
            userDto = userService.create(userDto);
        } catch (jp.co.sparkworks.restaurant.backoffice.exception.BusinessException be) {
            result.reject(be.toString());
            mv.setViewName("user/createConfirm");
            mv.addObject("userInputForm", userInputForm);
            return mv;
        }

        session.removeAttribute("userInputForm");

        mv.setViewName("user/createComplete");
        mv.addObject("userDto", userDto);
        return mv;
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView list(ModelAndView mv, UserInputForm userInputForm) {
        mv.setViewName("user/list");
        List<UserDto> userDtoList = userService.search();
        mv.addObject("userDtoList", userDtoList);

        return mv;
    }

    @PostMapping("/list")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView search() {

        ModelAndView mv = new ModelAndView("user/list");
        return mv;
    }

    @GetMapping("/detail")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView detail(@RequestParam int userId, ModelAndView mv) {

        UserDto userDto = userService.getById(Long.valueOf(userId));

        mv.setViewName("user/detail");
        mv.addObject("userDto", userDto);

        return mv;
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView toUpdate(@RequestParam Long userId, ModelAndView mv, @Validated UserInputForm userInputForm, BindingResult result) {

        UserDto userDto = null;
        try {
            userDto = userService.getById(userId);
        } catch (BusinessException be) {
            result.reject(ErrorCodeConstant.E50012);
            mv.setViewName("user/list");
            List<UserDto> userDtoList = userService.search();
            mv.addObject("userDtoList", userDtoList);
            return mv;
        }

        userInputForm.setUserId(userDto.getUserId());
        userInputForm.setUserName(userDto.getUserName());
        userInputForm.setEmail(userDto.getEmail());
        userInputForm.setRoleId(userDto.getRoleId());
        userInputForm.setPassword(userDto.getPassword());

        mv.addObject("userInputForm", userInputForm);
        mv.setViewName("user/update");
        return mv;

    }

    @PostMapping("/updateConfirm")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView updateConfirm(UserInputForm userInputForm) {

        ModelAndView mv = new ModelAndView("user/updateConfirm");
        session.setAttribute("userInputForm", userInputForm);
        return mv;

    }

    @PostMapping("/updateComplete")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView update(@Validated UserInputForm userInputForm, BindingResult result) {

        UserInputForm uif = (UserInputForm) session.getAttribute("userInputForm");
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(uif, userInputForm);
        userDto.setUserId(uif.getUserId());
        userDto.setUserName(uif.getUserName());
        userDto.setEmail(uif.getEmail());
        userDto.setRoleId(uif.getRoleId());
        userDto.setPassword(uif.getPassword());
        try {
            userService.update(userDto);
        } catch (BusinessException be) {
            result.reject(ErrorCodeConstant.E50011);
            ModelAndView mv = new ModelAndView("user/updateConfirm");
            mv.addObject("userInputForm", userInputForm);
            return mv;
        }

        session.removeAttribute("userInputForm");

        ModelAndView mv = new ModelAndView("user/updateComplete");
        mv.addObject("userInputForm", userInputForm);

        return mv;

    }

    @PostMapping("/deleteConfirm")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_DELETE + "')")
    public ModelAndView deleteConfirm(@RequestParam Long userId, ModelAndView mv, @Validated UserInputForm userInputForm, BindingResult result) {

        UserDto userDto = new UserDto();
        try {
            userDto = userService.getById(userId);
        } catch (BusinessException be) {
            result.reject(ErrorCodeConstant.E50012);
            mv.setViewName("user/list");
            List<UserDto> userDtoList = userService.search();
            mv.addObject("userDtoList", userDtoList);
            return mv;
        }
        mv.setViewName("user/deleteConfirm");
        mv.addObject("userDto", userDto);
        return mv;

    }

    @PostMapping("/deleteComplete")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_DELETE + "')")
    public ModelAndView deleteComplete(@RequestParam Long userId, ModelAndView mv) {

        UserDto userDto = userService.getById(userId);
        userService.delete(userId);
        mv.addObject("userDto", userDto);
        mv.setViewName("user/deleteComplete");

        return mv;
    }

    @PostMapping("/returnToUserList")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView returnToUserList(UserInputForm userInputForm) {
        ModelAndView mv = new ModelAndView("user/list");
        List<UserDto> userDtoList = userService.search();
        mv.addObject("userDtoList", userDtoList);
        return mv;
    }

    @PostMapping("/returnToUserDetail")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_VIEW + "')")
    public ModelAndView returnToUserDetail(@RequestParam Long userId, ModelAndView mv) {

        UserDto userDto = userService.getById(userId);
        mv.setViewName("user/detail");
        mv.addObject("userDto", userDto);
        return mv;
    }

    @PostMapping("/returnToUpdate")
    @PreAuthorize("hasAuthority('" + jp.co.sparkworks.restaurant.backoffice.constant.AuthConstant.USER_UPDATE + "')")
    public ModelAndView returnToUpdate() {

        UserInputForm userInputForm = (UserInputForm) session.getAttribute("userInputForm");
        ModelAndView mv = new ModelAndView("user/update");
        mv.addObject("userInputForm", userInputForm);
        return mv;

    }

}
