package org.team.bookshop.domain.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.team.bookshop.domain.order.dto.CreateDeliveryRequest;
import org.team.bookshop.domain.order.repository.DeliveryRepository;
import org.team.bookshop.domain.order.repository.OrderRepository;
import org.team.bookshop.domain.user.repository.AddressRepository;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/delivery")
public class DeliveryController {

  private final DeliveryRepository deliveryRepository;
  private final OrderRepository orderRepository;
  private final AddressRepository addressRepository;

  @PostMapping("/create")
  public void createDelivery(@RequestBody CreateDeliveryRequest createDeliveryRequest) {
    return;
  }

}