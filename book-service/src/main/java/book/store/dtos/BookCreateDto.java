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
public class BookCreateDto {

    @NotNull(message = "title required")
    private String title;

    @NotNull(message = "author required")
    private String author;

    @NotNull(message = "isbn required")
    private String isbn;

    @NotNull(message = "price required")
    private Double price;
}
