package book.store.service;


import book.store.dtos.OrderDto;
import book.store.entity.OrderEntity;
import book.store.enums.OrderStatus;
import book.store.exp.AppBadRequestException;
import book.store.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderDto create(Long userId, Long bookId) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUserId(userId);
        orderEntity.setBookId(bookId);
        orderEntity.setStatus(OrderStatus.ACTIVE);
        orderRepository.save(orderEntity);
        return new OrderDto(orderEntity.getId(), orderEntity.getBookId(), orderEntity.getUserId(), orderEntity.getStatus());
    }

    public OrderDto update(Long userId, Long orderId) {
        OrderEntity orderEntity = getId(userId, orderId);
        orderEntity.setStatus(OrderStatus.NON_ACTIVE);
        orderRepository.save(orderEntity);
        return new OrderDto(orderEntity.getId(), orderEntity.getBookId(), orderEntity.getUserId(), orderEntity.getStatus());
    }

    public OrderDto get(Long userId, Long orderId) {
        OrderEntity orderEntity = getId(userId, orderId);
        return new OrderDto(orderEntity.getId(), orderEntity.getBookId(), orderEntity.getUserId(), orderEntity.getStatus());
    }

    private OrderEntity getId(Long userId, Long orderId) {
        Optional<OrderEntity> optionalOrder = orderRepository.findByIdAndUserId(orderId, userId);
        if (optionalOrder.isEmpty()) {
            log.info("order not found : {}", orderId);
            throw new AppBadRequestException("order not found !!!");
        }
        return optionalOrder.get();
    }


}
