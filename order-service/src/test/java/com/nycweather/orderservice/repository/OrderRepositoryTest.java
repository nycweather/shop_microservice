//package com.nycweather.orderservice.repository;
//
//import com.nycweather.orderservice.dto.SingleOrderItemResponseDTO;
//import com.nycweather.orderservice.model.Order;
//import com.nycweather.orderservice.model.OrderItem;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
//class OrderRepositoryTest {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Test
//    void OrderRepository_getOrderItemsByOrderId_getOrderGivenOrderId() {
//        // Given
//        OrderItem orderItem = OrderItem.builder()
//                .productName("Product 1")
//                .sku("abcd-1234")
//                .quantity(2)
//                .price(10.49)
//                .build();
//
//        Order order = Order.builder()
//                .customerEmail("customer@email.com")
//                .orderNumber(1L)
//                .build();
//
//        orderItem.setOrder(order); // Assuming a bidirectional relationship
//        order.setOrderItems(List.of(orderItem)); // Properly set the relationship
//
//        // When
//        Order savedOrder = orderRepository.save(order);
//
//        // Then
//        assertThat(savedOrder).isNotNull();
//        assertThat(savedOrder.getOrderItems()).isNotEmpty();
//        assertThat(savedOrder.getOrderItems().get(0).getSku()).isEqualTo("abcd-1234");
//
//    }
//
//    @Test
//    void OrderRepository_getOrderInformation_givenSkuAndEmail() {
//        // Given
//        OrderItem orderItem = new OrderItem();
//        orderItem.setProductName("Product 1");
//        orderItem.setSku("abcd-1234");
//        orderItem.setQuantity(2);
//        orderItem.setPrice(10.49);
//
//        Order order = new Order();
//        order.setCustomerEmail("customer@email.com");
//        order.setOrderNumber(1L);
//
//        // Set the relationship
//        orderItem.setOrder(order); // Assuming a bidirectional relationship
//        order.setOrderItems(List.of(orderItem)); // Properly set the relationship
//
////        orderRepository.save(order);
//        orderRepository.saveAndFlush(order);
//
//        // When
//        List<SingleOrderItemResponseDTO> orderDetails = orderRepository.getOrderInformation("abcd-1234", "customer@email.com");
//
//        // Then
//        assertThat(orderDetails).isNotNull();
//        assertThat(orderDetails).hasSize(1); // This will ensure that there's exactly one element before accessing it.
//
//// Now safe to retrieve since we've asserted the size
//        SingleOrderItemResponseDTO retrievedItem = orderDetails.get(0);
//        assertThat(retrievedItem.productName()).isEqualTo("Product 1");
//        assertThat(retrievedItem.sku()).isEqualTo("abcd-1234");
//        assertThat(retrievedItem.customerEmail()).isEqualTo("customer@email.com");
//    }
//}
