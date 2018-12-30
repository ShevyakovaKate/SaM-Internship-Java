package com.samsolutions.service;

import com.samsolutions.dto.OrderDTO;
import com.samsolutions.dto.OrderPayStatusDTO;

import java.util.List;

public interface OrderService {
    /**
     * Method is find the all orders and convert them into DTO.
     * @return list of {@link OrderDTO}
     */
    List<OrderDTO> getAll();

    /**
     * Method is find the orders by peameters and convert them into DTO.
     * @param status of orders
     * @param period period of paying //TODO time of paying needed
     * @param sort sorting by field
     * @return list of {@link OrderDTO}
     */
    List<OrderDTO> getByParameter(String status, Integer period, String sort);

    /**
     * Method is find the all orders of user.
     * @return list of {@link OrderDTO}
     */
    List<OrderDTO> getOrdersByUser(Long id);

    /**
     * od is find the all orders of authorized user.
     * @return list of {@link OrderDTO}
     */
    List<OrderDTO> getOrdersByUser();

    /**
     * Method is find the order by id and convert it into DTO.
     * @param id identifier of order.
     * @return {@link OrderDTO}.
     */
    OrderDTO getById(Long id);

    /**
     * Method is fill all fields of new order dto,
     * add them to data base, return new added order dto.
     * @param dto order will be added.
     * @return {@link OrderDTO}
     */
    OrderDTO add(OrderDTO dto);

    /**
     * Method update the order status field,
     * and return the updated dto.
     * @param status {@link OrderPayStatusDTO}.
     * @param id identifier of order will be updated.
     * @return updated {@link OrderDTO}.
     */
    OrderDTO update(OrderPayStatusDTO status, Long id);

    /**
     * Method is delete the order.
     * @param dto order will be deleted.
     */
    void delete(OrderDTO dto);

    /**
     * Method is delete the order by id number.
     * @param id identifier of order will be deleted.
     */
    void deleteById(Long id);
}
