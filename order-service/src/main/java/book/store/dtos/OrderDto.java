package book.store.dtos;

import book.store.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    private Long id;
    private Long bookId;
    private Long userId;
    private OrderStatus status;

    public OrderDto(Long id, Long bookId, Long userId, OrderStatus status) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.status = status;
    }

    public OrderDto() {
    }
}
