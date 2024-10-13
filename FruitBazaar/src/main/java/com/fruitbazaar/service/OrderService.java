package com.fruitbazaar.service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fruitbazaar.common.result.PageResult;
import com.fruitbazaar.mapper.OrderMapper;
import com.fruitbazaar.model.dto.OrderPageQueryDTO;
import com.fruitbazaar.model.entity.Orders;
import com.fruitbazaar.service.interf.IOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService extends ServiceImpl<OrderMapper, Orders> implements IOrder {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageResult pageQuery(OrderPageQueryDTO orderPageQueryDTO) {
        Page<Orders> page = new Page<>(orderPageQueryDTO.getPage(), orderPageQueryDTO.getLimit());
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        // 根据需要拼接查询条件
        if (!StringUtils.isEmpty(orderPageQueryDTO.getCode())) {
            wrapper.like(Orders::getCode, orderPageQueryDTO.getCode());
        }
        // 可以继续根据其他条件进行查询条件的拼接
        page = this.orderMapper.selectPage(page, wrapper);
        long total = this.orderMapper.selectCount(wrapper);
        List<Orders> records = page.getRecords();
        return new PageResult(total, records);
    }

    /**
//     * 根据客户ID获取订单
//     * @param userId 客户ID
//     * @return 订单列表
//     */
//    @Override
//    public List<Orders> getOrderById(int userId) {
//        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(Orders::getUserId,userId);
//        return orderMapper.list(wrapper);
//    }

}
