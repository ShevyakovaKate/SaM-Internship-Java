package com.samsolutions.controller;


import com.samsolutions.dto.GiftCertificateDTO;
import com.samsolutions.dto.GiftTagDTO;
import com.samsolutions.dto.OrderDTO;
import com.samsolutions.service.GiftCertService;
import com.samsolutions.service.GiftTagService;
import com.samsolutions.service.OrderService;
import com.samsolutions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private GiftCertService giftCertService;

    @Autowired
    private GiftTagService giftTagService;

    @Autowired
    private UserService userService;

    @GetMapping("/admin/user/{id}/orders")
    public ModelAndView getUserOrdersPage(@PathVariable Long id) {
        return new ModelAndView("admin/orders")
                .addObject("orderList",  orderService.getOrdersByUser(id));
    }

    @GetMapping("/admin/orders")
    public ModelAndView getOrdersPage() {
        return new ModelAndView("admin/orders")
                .addObject("orderList",  orderService.getAll());
    }

    @GetMapping("/admin/certificates")
    public ModelAndView getCertificatesAdminPage() {
        return new ModelAndView("admin/certificates").addObject("certificateList",
                giftCertService.getAll());
    }

    @GetMapping("/admin/tags")
    public ModelAndView getTagsAdminPage() {
        List<GiftTagDTO> all = giftTagService.getAll();
        return new ModelAndView("admin/tags").addObject("tagList",
                all );
    }

    @GetMapping("/admin/certificate/add")
    public ModelAndView getCertificateAddPage() {
        return new ModelAndView("admin/certificate-add").addObject("certificate",
                new GiftCertificateDTO());
    }

    @GetMapping("admin/certificates/{id}")
    public ModelAndView getCertificatePage(@PathVariable Long id) {
        return new ModelAndView("admin/certificate-edit").addObject("certificate",
                giftCertService.getById(id));
    }

    @GetMapping("/admin/tags/add")
    public ModelAndView getTagAddPage() {
        return new ModelAndView("admin/tags-add").addObject("tag",
                new GiftTagDTO());
    }

    @GetMapping("admin/tags/{id}")
    public ModelAndView getTagPage(@PathVariable Long id) {
        return new ModelAndView("admin/tags-edit").addObject("tag",
                giftTagService.getById(id));
    }
}
