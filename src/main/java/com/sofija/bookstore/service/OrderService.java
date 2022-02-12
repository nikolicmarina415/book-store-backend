package com.sofija.bookstore.service;

import com.sofija.bookstore.constants.Constants;
import com.sofija.bookstore.model.*;
import com.sofija.bookstore.repository.OrderEntryRepository;
import com.sofija.bookstore.repository.OrderRepository;
import com.sofija.bookstore.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class OrderService {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private OrderEntryRepository orderEntryRepository;

    @Resource
    private OrderStatusRepository orderStatusRepository;

    @Resource
    private UserService userService;

    @Value("${golden.customer.threshold}")
    private double goldenCustomerThreshold;

    @Value("${golden.customer.discount}")
    private double goldenCustomerDiscount;

    public List<OrderModel> getAll() {
        return orderRepository.findAll();
    }

    public OrderModel create(OrderModel orderModel, List<OrderEntryModel> orderEntryModels) {
        OrderModel createdOrderModel = createOrder(orderModel);
        saveOrderEntries(createdOrderModel, orderEntryModels);
        promoteUserToGoldenIfConditionMet(orderModel);
        return createdOrderModel;
    }

    public List<OrderModel> getAllByUserId(int userId) {
        if (userService.isAdmin(userId)) {
            return getAll();
        }
        return orderRepository.findAllByUserId(userId);
    }

    public void completeOrder(int id) {
        OrderStatusModel completedOrderStatusModel = orderStatusRepository.findByName(Constants.OrderStatus.COMPLETED);
        orderRepository.completeOrder(id, completedOrderStatusModel.getId());
    }

    private OrderModel createOrder(OrderModel orderModel) {
        if (orderModel.getUserModel() == null) {
            orderModel.setUserModel(userService.getAnonymousUser());
        }
        orderModel.setOrderStatusModel(getPendingOrderStatusModel());
        return orderRepository.save(orderModel);
    }

    private void saveOrderEntries(OrderModel createdOrderModel, List<OrderEntryModel> orderEntryModels) {
        orderEntryModels.forEach(orderEntryModel -> {
            orderEntryModel.setOrder(createdOrderModel);
            orderEntryModel.setPrice(getOrderEntryPrice(createdOrderModel.getUserModel().getId(), orderEntryModel));
        });
        orderEntryRepository.saveAll(orderEntryModels);
        createdOrderModel.setOrderEntryModels(orderEntryModels);
    }

    private void promoteUserToGoldenIfConditionMet(OrderModel orderModel) {
        int userId = orderModel.getUserModel().getId();
        if (userService.isGoldenCustomer(userId)) {
            return;
        }

        if (getTotalSumForOrder(orderModel) >= goldenCustomerThreshold) {
            userService.addRoleToUser(Constants.Role.GOLDEN_CUSTOMER, userId);
            return;
        }

        List<OrderModel> orderModels = orderRepository.findAllByUserId(userId);
        double totalSum = getTotalSum(orderModels);
        if (totalSum >= goldenCustomerThreshold) {
            userService.addRoleToUser(Constants.Role.GOLDEN_CUSTOMER, userId);
        }
    }

    private double getTotalSum(List<OrderModel> completedOrders) {
        return completedOrders.stream()
                .mapToDouble(this::getTotalSumForOrder)
                .sum();
    }

    private double getTotalSumForOrder(OrderModel orderModel) {
        return orderModel.getOrderEntryModels()
                .stream()
                .mapToDouble(OrderEntryModel::getPrice)
                .sum();
    }

    private double getOrderEntryPrice(int userId, OrderEntryModel orderEntryModel) {
        double orderEntryPrice = orderEntryModel.getBookModel().getPrice() * orderEntryModel.getQuantity();
        if (userService.isGoldenCustomer(userId)) {
            return orderEntryPrice - (orderEntryPrice * goldenCustomerDiscount);
        }
        return orderEntryPrice;
    }

    private OrderStatusModel getPendingOrderStatusModel() {
        return orderStatusRepository.findByName(Constants.OrderStatus.PENDING);
    }
}
