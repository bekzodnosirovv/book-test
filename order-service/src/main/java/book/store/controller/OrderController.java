package book.store.controller;

import book.store.dtos.OrderCreateDto;
import book.store.dtos.OrderDto;
import book.store.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "Order api list", description = "Api list for create, update, get")
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "create book", description = "")
    @PostMapping("/create")
    public ResponseEntity<OrderDto> create(@RequestParam(value = "userId") Long userId,
                                           @RequestParam(value = "bookid") Long bookId) {
        log.info("create order : {}", "");
        return ResponseEntity.ok(orderService.create(userId, bookId));
    }

    @Operation(summary = "update order", description = "")
    @PostMapping("/update")
    public ResponseEntity<OrderDto> update(@RequestParam(value = "userId") Long userId,
                                           @RequestParam(value = "orderId") Long orderId) {
        log.info("update order : {}", orderId);
        return ResponseEntity.ok(orderService.update(userId, orderId));
    }

    @Operation(summary = "get order", description = "")
    @GetMapping("/get")
    public ResponseEntity<OrderDto> get(@RequestParam(value = "userId") Long userId,
                                        @RequestParam(value = "orderId") Long orderId) {
        log.info("get order : {}", orderId);
        return ResponseEntity.ok(orderService.get(userId, orderId));
    }
}