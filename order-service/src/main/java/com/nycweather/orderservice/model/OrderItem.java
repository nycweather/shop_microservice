package com.nycweather.orderservice.model;

import com.nycweather.orderservice.dto.SingleOrderItemResponseDTO;
import jakarta.persistence.*;
import lombok.*;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Order.getOrderInformation",
                query = "SELECT " +
                        "oit.order_id, " +
                        "oit.product_name, " +
                        "oit.sku, " +
                        "oit.price, " +
                        "oit.quantity, " +
                        "ot.order_date, " +
                        "ot.customer_email " +
                        "FROM order_table ot " +
                        "LEFT JOIN ordered_item_table oit " +
                        "ON ot.order_id = oit.order_id " +
                        "WHERE oit.sku = :sku AND ot.customer_email = :email",
                resultSetMapping = "OrderItemResultMapping"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "OrderItemResultMapping",
                classes = @ConstructorResult(
                        targetClass = SingleOrderItemResponseDTO.class,
                        columns = {
                                @ColumnResult(name = "order_id", type = String.class),
                                @ColumnResult(name = "product_name", type = String.class),
                                @ColumnResult(name = "sku", type = String.class),
                                @ColumnResult(name = "price", type = Double.class),
                                @ColumnResult(name = "quantity", type = Integer.class),
                                @ColumnResult(name = "order_date", type = java.sql.Date.class),
                                @ColumnResult(name = "customer_email", type = String.class)
                        }
                )
        )
})

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ordered_item_table")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String sku;
    private String productId;
    private String productName;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;
    private Double price;
    private Integer quantity;
}
