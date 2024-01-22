package book.store.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Double price;

    public BookDto(Long id, String title, String author, String isbn, Double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
    }

    public BookDto() {
    }
}
