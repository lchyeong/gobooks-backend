package org.team.bookshop.domain.order.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.team.bookshop.domain.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

  @Query("select o from Order o " +
          "join fetch o.orderItems oi " +
          "join fetch o.delivery d " +
          "join fetch d.address " +
          "join fetch oi.product " +
          "where o.id = :orderId")
  Order findWithAllRelatedEntityById(@Param("orderId") Long orderId);

  @Query("select o from Order o " +
          "join fetch o.orderItems oi " +
          "join fetch oi.product " +
          "where o.id = :orderId")
  Order findWithOrderItems(@Param("orderId") long orderId);

    Optional<Order> findByMerchantId(String merchantId);

    void deleteByDeliveryId(Long deliveryId);
}
