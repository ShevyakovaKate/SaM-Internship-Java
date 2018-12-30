package com.samsolutions.controller;

import com.samsolutions.dto.OrderDTO;
import com.samsolutions.dto.OrderPayStatusDTO;
import com.samsolutions.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private static final String ORDERLIST_ATTRIBUTE = "orderList";
    private static final String ADMIN_ORDERS_PAGE = "admin/orders";

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ModelAndView getAll (@RequestParam(value = "status", required = false) String status,
                                @RequestParam(value = "period", required = false) String period,
                                @RequestParam(value = "sort", required = false) String sort) {
        ModelAndView modelAndView = new ModelAndView(ADMIN_ORDERS_PAGE).addObject(ORDERLIST_ATTRIBUTE, Collections.emptyList());
        if (status != null || period != null || sort != null) {
           modelAndView.addObject(ORDERLIST_ATTRIBUTE, orderService.getByParameter(status, period, sort));
        }
        else {
            modelAndView.addObject(ORDERLIST_ATTRIBUTE, orderService.getAll());
        }
        return modelAndView;
    }

    @GetMapping(value = "/{id}")
    public OrderDTO getOrder(@PathVariable Long id) {
        return orderService.getById(id);
    }

    @PostMapping
    public OrderDTO addOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.add(orderDTO);
    }

    @PutMapping(value = "/{id}")
    public OrderDTO updateOrder(@RequestBody OrderPayStatusDTO status, @PathVariable Long id) {
        return orderService.update(status, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteById(id);
    }


    @DeleteMapping
    public void deleteOrder(@RequestBody OrderDTO orderDTO) {
        orderService.delete(orderDTO);
    }

}
