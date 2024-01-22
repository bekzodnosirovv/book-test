package book.store.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderCreateDto {

    @NotNull(message = "user id required")
    private Long userId;

    @NotNull(message = "book id required")
    private Long bookId;

    @NotNull(message = "price required")
    private Double price;
}
