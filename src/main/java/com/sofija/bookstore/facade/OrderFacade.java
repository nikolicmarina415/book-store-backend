package com.sofija.bookstore.facade;

import com.sofija.bookstore.data.OrderData;
import com.sofija.bookstore.data.OrderEntryData;
import com.sofija.bookstore.data.OrderStatusData;
import com.sofija.bookstore.model.OrderEntryModel;
import com.sofija.bookstore.model.OrderModel;
import com.sofija.bookstore.model.OrderStatusModel;
import com.sofija.bookstore.service.OrderService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderFacade {

    @Resource
    private UserFacade userFacade;

    @Resource
    private BookFacade bookFacade;

    @Resource
    private OrderService orderService;

    public List<OrderData> getAll() {
        return orderService.getAll()
                .stream()
                .map(this::createOrderData)
                .collect(Collectors.toList());
    }

    public OrderData create(OrderData orderData) {
        OrderModel orderModel = createOrderModel(orderData);
        List<OrderEntryModel> orderEntryModels = createOrderEntryModels(orderData.getOrderEntryDataList());
        OrderModel createdOrderModel = orderService.create(orderModel, orderEntryModels);
        return createOrderData(createdOrderModel);
    }

    public List<OrderData> getAllByUserId(int userId) {
        return orderService.getAllByUserId(userId)
                .stream()
                .map(this::createOrderData)
                .collect(Collectors.toList());
    }

    public void completeOrder(int id) {
        orderService.completeOrder(id);
    }

    private OrderData createOrderData(OrderModel orderModel) {
        OrderData orderData = new OrderData();
        orderData.setId(orderModel.getId());
        orderData.setOrderStatusData(createOrderStatusData(orderModel.getOrderStatusModel()));
        orderData.setOrderEntryDataList(createOrderEntryDataList(orderData, orderModel.getOrderEntryModels()));
        orderData.setAddress(orderModel.getAddress());
        orderData.setCity(orderModel.getCity());
        orderData.setCountry(orderModel.getCountry());
        orderData.setUserData(userFacade.createUserData(orderModel.getUserModel()));
        return orderData;
    }

    private OrderStatusData createOrderStatusData(OrderStatusModel orderStatusModel) {
        OrderStatusData orderStatusData = new OrderStatusData();
        orderStatusData.setId(orderStatusModel.getId());
        orderStatusData.setName(orderStatusModel.getName());
        return orderStatusData;
    }

    private List<OrderEntryData> createOrderEntryDataList(OrderData orderData, List<OrderEntryModel> orderEntryModels) {
        return orderEntryModels.stream()
                .map(orderEntryModel -> createOrderEntryData(orderData, orderEntryModel))
                .collect(Collectors.toList());
    }

    private OrderEntryData createOrderEntryData(OrderData orderData, OrderEntryModel orderEntryModel) {
        OrderEntryData orderEntryData = new OrderEntryData();
        orderEntryData.setOrderData(orderData);
        orderEntryData.setBookData(bookFacade.createBookData(orderEntryModel.getBookModel()));
        orderEntryData.setQuantity(orderEntryModel.getQuantity());
        orderEntryData.setId(orderEntryModel.getId());
        orderEntryData.setPrice(orderEntryModel.getPrice());
        return orderEntryData;
    }

    private OrderModel createOrderModel(OrderData orderData) {
        OrderModel orderModel = new OrderModel();
        orderModel.setAddress(orderData.getAddress());
        orderModel.setCountry(orderData.getCountry());
        orderModel.setCity(orderData.getCity());
        orderModel.setUserModel(userFacade.createUserModel(orderData.getUserData()));
        return orderModel;
    }

    private List<OrderEntryModel> createOrderEntryModels(List<OrderEntryData> orderEntryDataList) {
        return orderEntryDataList.stream()
                .map(this::createOrderEntryModel)
                .collect(Collectors.toList());
    }

    private OrderEntryModel createOrderEntryModel(OrderEntryData orderEntryData) {
        OrderEntryModel orderEntryModel = new OrderEntryModel();
        orderEntryModel.setBookModel(bookFacade.createBookModel(orderEntryData.getBookData()));
        orderEntryModel.setQuantity(orderEntryData.getQuantity());
        return orderEntryModel;
    }
}
