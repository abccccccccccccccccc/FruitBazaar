package com.fruitbazaar.service.interf;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fruitbazaar.common.result.PageResult;
import com.fruitbazaar.model.dto.OrderPageQueryDTO;
import com.fruitbazaar.model.entity.Orders;

import java.util.List;

public interface IOrder extends IService<Orders> {

    /**
     * 分页查询订单
     * @param orderPageQueryDTO 查询条件DTO
     * @return 分页结果
     */
    PageResult pageQuery(OrderPageQueryDTO orderPageQueryDTO);

//    /**
//     * 根据客户ID获取订单
//     * @param userId 客户ID
//     * @return 订单列表
//     */
//    List<Orders> getByUserId(int userId);
//
//    /**
//     * 新增订单
//     * @param orders 订单对象
//     * @return 是否新增成功
//     */
//    boolean addOrder(Orders orders);
//
//    /**
//     * 根据订单ID删除订单（逻辑删除）
//     * @param orderId 订单ID
//     * @return 是否删除成功
//     */
//    boolean deleteOrder(Long orderId);
}
