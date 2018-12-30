package com.samsolutions.service.impl;

import com.samsolutions.converter.OrderConverter;
import com.samsolutions.converter.OrderPayStatusConverter;
import com.samsolutions.dao.OrderDao;
import com.samsolutions.dao.PayStatusDao;
import com.samsolutions.dto.GiftCertificateDTO;
import com.samsolutions.dto.OrderDTO;
import com.samsolutions.dto.OrderPayStatusDTO;
import com.samsolutions.dto.UserDTO;
import com.samsolutions.entity.OrderEntity;
import com.samsolutions.entity.OrderPayStatusEntity;
import com.samsolutions.service.GiftCertService;
import com.samsolutions.service.OrderService;
import com.samsolutions.service.PayStatusService;
import com.samsolutions.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final String DEFAULT_ORDER_STATUS = "WAIT";
    private static final int DAY_PERIOD = 1;
    private static final int WEEK_PERIOD = 7;
    private static final int MONTH_PERIOD = 31;

    private OrderDao orderDao;
    private GiftCertService giftCertService;
    private UserService userService;
    private UserDetailsServiceImpl userDetailsService;
    private PayStatusService payStatusService;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao,
                            GiftCertService giftCertService,
                            UserService userService,
                            UserDetailsServiceImpl userDetailsService,
                            PayStatusService payStatusService) {
        this.orderDao = orderDao;
        this.giftCertService = giftCertService;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.payStatusService = payStatusService;
    }

    @Override
    public List<OrderDTO> getAll() {
        List<OrderEntity> all = orderDao.findAll();
        return OrderConverter.toDtoList(all);
    }

    @Override
    public List<OrderDTO> getByParameter(String status, Integer period, String sort) {
        LocalDateTime fromDate = null;
        switch (period) {
            case DAY_PERIOD : fromDate = LocalDateTime.now().minusDays(DAY_PERIOD);
            case WEEK_PERIOD: fromDate = LocalDateTime.now().minusDays(WEEK_PERIOD);
            case MONTH_PERIOD: fromDate = LocalDateTime.now().minusDays(MONTH_PERIOD);
        }
        List<OrderEntity> entityList = orderDao.findByParameter(status, fromDate, sort);
        return OrderConverter.toDtoList(entityList);
    }

    @Override
    public OrderDTO getById(Long id) {
        OrderEntity orderEntity = orderDao.find(id);
        return OrderConverter.toDto(orderEntity);
    }

    @Override
    public List<OrderDTO> getOrdersByUser(Long id) {
        List<OrderEntity> entityList = orderDao.findByUserId(id);
        return OrderConverter.toDtoList(entityList);
    }

    @Override
    public List<OrderDTO> getOrdersByUser() {
        UserDTO currentUser = userDetailsService.getCurrentUser();
        List<OrderEntity> entityList = orderDao.findByUserId(currentUser.getId());
        return OrderConverter.toDtoList(entityList);
    }

    @Override
    public OrderDTO add(OrderDTO dto) {
        Long certificateId = dto.getGiftCertificate().getId();
        String login = dto.getUser().getLogin();
        GiftCertificateDTO giftCertificateDTO = giftCertService.getById(certificateId);
        UserDTO userDTO = userService.getByName(login);

        dto.setGiftCertificate(giftCertificateDTO);
        dto.setUser(userDTO);
        OrderPayStatusDTO payStatusDTO = payStatusService.getByName(DEFAULT_ORDER_STATUS);
        dto.setPayStatus(payStatusDTO);
        dto.setDateTime(LocalDateTime.now());

        OrderEntity orderEntity = OrderConverter.toEntity(dto);
        OrderEntity savedEntity = orderDao.save(orderEntity);
        return OrderConverter.toDto(savedEntity);
    }

    @Override
    public OrderDTO update(OrderPayStatusDTO status, Long id) {

        OrderEntity orderEntityFromDB = orderDao.find(id);
        OrderPayStatusDTO payStatusDTO = payStatusService.getByName(status.getName());
        OrderPayStatusEntity orderPayStatusEntity = OrderPayStatusConverter.toEntity(payStatusDTO);
        orderEntityFromDB.setPayStatus(orderPayStatusEntity);

        OrderEntity updatedEntity = orderDao.update(orderEntityFromDB);
        return OrderConverter.toDto(updatedEntity);
    }


    @Override
    public void delete(OrderDTO dto) {
        OrderEntity orderEntity = OrderConverter.toEntity(dto);
        orderDao.delete(orderEntity);
    }

    @Override
    public void deleteById(Long id) {
        orderDao.deleteById(id);
    }
}
