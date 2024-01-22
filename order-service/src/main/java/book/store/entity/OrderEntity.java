package book.store.entity;

import book.store.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
