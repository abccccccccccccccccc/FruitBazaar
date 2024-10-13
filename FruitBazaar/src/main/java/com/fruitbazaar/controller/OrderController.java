package com.fruitbazaar.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fruitbazaar.common.constant.MessageConstant;
import com.fruitbazaar.common.result.PageResult;
import com.fruitbazaar.common.result.Result;
import com.fruitbazaar.model.dto.EmployeeLoginDTO;
import com.fruitbazaar.model.dto.OrderPageQueryDTO;
import com.fruitbazaar.model.entity.Employee;
import com.fruitbazaar.model.entity.Orders;
import com.fruitbazaar.service.interf.IOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@Api(tags = "订单管理")
@Slf4j
public class OrderController {

    @Autowired
    private IOrder orderService;

    @GetMapping("/page")
    @ApiOperation("订单分页查询")
    public Result<PageResult> page(OrderPageQueryDTO orderPageQueryDTO) {
        log.info("订单分页查询，参数为：{}", orderPageQueryDTO);
        PageResult pageResult = orderService.pageQuery(orderPageQueryDTO);
        return Result.success(pageResult);
    }

//    @GetMapping("/{orderId}")
//    @ApiOperation("根据订单ID查询订单详情")
//    public Result<Orders> getOrderById(@PathVariable Long orderId) {
//        log.info("根据订单ID查询订单详情，orderId：{}", orderId);
//        Orders order = orderService.getOrderById(orderId);
//        return Result.success(order);
//    }


//    @ApiOperation("下单")
//    @PostMapping("/add")
//    public Result add(@RequestBody Orders orders){
//        log.info("\n新增订单：{}",orders);
//        boolean res=orderService.save(orders);
//        return res?Result.success():Result.error(MessageConstant.OPTION_ERROR);
//    }

    @ApiOperation("下单")
    @PostMapping("/add")
    public String add(@RequestBody Orders orders){
        log.info("\n新增订单：{}",orders);
        return "success";
    }

    @ApiOperation("根据客户id查询订单")
    @PostMapping("/getById")
    public Result getById(@RequestBody int userId){
        log.info("\n客户id：{}",userId);
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Orders::getUserId,userId);
        List<Orders> data=orderService.list(wrapper);
        return Result.success(data);
    }
}
