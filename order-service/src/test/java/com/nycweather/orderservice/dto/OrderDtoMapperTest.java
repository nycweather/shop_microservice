package com.nycweather.orderservice.dto;

import com.nycweather.orderservice.model.Order;
import com.nycweather.orderservice.model.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class OrderDtoMapperTest {
    private OrderDtoMapper orderDtoMapper;

    @BeforeEach
    void setUp() {
        orderDtoMapper = new OrderDtoMapper();
    }

    @Test
    void OrderDtoMapperTest_OrderItemToOrderItemResponseDTO_ReturnOrderResponseItemDTOGivenOrderItem() {
        // Given
        OrderItem orderItem = OrderItem.builder()
                .productName("Product 1")
                .sku("abcd-1234")
                .quantity(2)
                .price(10.49)
                .build();
        // When
        OrderResponseItemDTO orderItemResponseDTO = orderDtoMapper.orderItemToOrderItemResponseDTO(orderItem);
        // Then
        assertThat(orderItemResponseDTO.productName()).isEqualTo("Product 1");
        assertThat(orderItemResponseDTO.sku()).isEqualTo("abcd-1234");
        assertThat(orderItemResponseDTO.quantity()).isEqualTo(2);
        assertThat(orderItemResponseDTO.price()).isEqualTo(10.49);
    }

    @Test
    void OrderDtoMapperTest_toOrderResponseDTO_ReturnWholeResponseGivenOrder() {
        // Given
        OrderItem orderItem1 = OrderItem.builder()
                .productName("Product 1")
                .sku("abcd-1234")
                .quantity(2)
                .price(10.49)
                .build();
        OrderItem orderItem2 = OrderItem.builder()
                .productName("Product 2")
                .sku("abcd-5678")
                .quantity(3)
                .price(11.49)
                .build();
        Order order = Order.builder()
                .customerEmail("@email.com")
                .orderDate(new Date())
                .orderNumber(1L)
                .orderItems(List.of(orderItem1, orderItem2))
                .build();
        // When 
        OrderResponseDTO orderResponseDTO = orderDtoMapper.toOrderResponseDTO(order);
        // Then
        assertThat(orderResponseDTO.customerEmail()).isEqualTo(order.getCustomerEmail());
        assertThat(orderResponseDTO.items()).isEqualTo(List.of(
                OrderResponseItemDTO.builder()
                        .productName("Product 1")
                        .sku("abcd-1234")
                        .quantity(2)
                        .price(10.49)
                        .build(),
                OrderResponseItemDTO.builder()
                        .productName("Product 2")
                        .sku("abcd-5678")
                        .quantity(3)
                        .price(11.49)
                        .build()
        ));
    }
}