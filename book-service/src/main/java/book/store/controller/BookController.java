package book.store.controller;

import book.store.dtos.BookCreateDto;
import book.store.dtos.BookDto;
import book.store.service.BookService;
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
@Tag(name = "Book api list", description = "Api list for create, update, delete, get")
public class BookController {

    private final BookService bookService;

    @Operation(summary = "create book", description = "")
    @PostMapping("/admin/create")
    public ResponseEntity<BookDto> create(@RequestBody @Valid BookCreateDto bookCreateDto) {
        log.info("create book : {}", bookCreateDto);
        return ResponseEntity.ok(bookService.create(bookCreateDto));
    }

    @Operation(summary = "update book", description = "")
    @PostMapping("/admin/update/{id}")
    public ResponseEntity<BookDto> update(@PathVariable Long id, @RequestBody @Valid BookCreateDto bookUpdateDto) {
        log.info("update book : {}", id);
        return ResponseEntity.ok(bookService.update(id,bookUpdateDto));
    }

    @Operation(summary = "delete book", description = "")
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<BookDto> delete(@PathVariable Long id) {
        log.info("delete book : {}", id);
        return ResponseEntity.ok(bookService.delete(id));
    }

    @Operation(summary = "get book", description = "")
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> get(@PathVariable Long id) {
        log.info("get book : {}", id);
        return ResponseEntity.ok(bookService.get(id));
    }
}