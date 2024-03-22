package com.nycweather.orderservice.repository;

import com.nycweather.orderservice.dto.SingleOrderItemResponseDTO;
import com.nycweather.orderservice.model.Order;
import com.nycweather.orderservice.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<OrderItem> getOrderItemsByOrderId(String orderId);

    @Query(name = "Order.getOrderInformation", nativeQuery = true)
    List<SingleOrderItemResponseDTO> getOrderInformation(@Param("sku") String productSku, @Param("email") String customerEmail);
}
