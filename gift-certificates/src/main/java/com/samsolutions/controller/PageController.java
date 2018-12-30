package com.samsolutions.controller;

import com.samsolutions.dto.GiftCertificateDTO;
import com.samsolutions.dto.GiftTagDTO;
import com.samsolutions.dto.OrderPayStatusDTO;
import com.samsolutions.dto.SignUpInformationDTO;
import com.samsolutions.service.GiftCertService;
import com.samsolutions.service.GiftTagService;
import com.samsolutions.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PageController {

    @Autowired
    private GiftCertService giftCertService;

    @Autowired
    private GiftTagService giftTagService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ModelAndView getGreetingPage() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getHomePage() {
//        logger.info("Welcome user! Requested Emp ID is: "+id);

        ModelAndView modelAndView = new ModelAndView("home");

        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView getSingUpPage() {
        return new ModelAndView("registration").addObject("informationDTO", new SignUpInformationDTO());
    }

    @GetMapping("/login")
    public ModelAndView getSingInPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/user/home")
    public ModelAndView getDefaultUserPage() {
        return new ModelAndView("user/home");
    }

    @GetMapping("/user/orders")
    public ModelAndView getUserOrdersPage() {
        return new ModelAndView("user/orders").
                addObject("orderList",  orderService.getOrdersByUser())
                .addObject("orderStatus", new OrderPayStatusDTO());
    }



    @GetMapping("/403")
    public ModelAndView getAccessDeniedPage() {
        return new ModelAndView("error/403");
    }

}
