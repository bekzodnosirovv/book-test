package book.store.service;


import book.store.dtos.BookCreateDto;
import book.store.dtos.BookDto;
import book.store.entity.BookEntity;
import book.store.exp.AppBadRequestException;
import book.store.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookDto create(BookCreateDto bookCreateDto) {
        checkIsbn(bookCreateDto.getIsbn());
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookCreateDto.getTitle());
        bookEntity.setAuthor(bookCreateDto.getAuthor());
        bookEntity.setIsbn(bookCreateDto.getIsbn());
        bookEntity.setPrice(bookCreateDto.getPrice());
        bookRepository.save(bookEntity);

        return new BookDto(bookEntity.getId(), bookEntity.getTitle(), bookEntity.getAuthor(), bookEntity.getIsbn(), bookEntity.getPrice());
    }

    public BookDto update(Long id, BookCreateDto bookUpdateDto) {
        checkIsbn(bookUpdateDto.getIsbn());
        BookEntity bookEntity = getById(id);
        bookEntity.setTitle(bookUpdateDto.getTitle());
        bookEntity.setAuthor(bookUpdateDto.getAuthor());
        bookEntity.setIsbn(bookUpdateDto.getIsbn());
        bookEntity.setPrice(bookUpdateDto.getPrice());
        bookRepository.save(bookEntity);

        return new BookDto(bookEntity.getId(), bookEntity.getTitle(),
                bookEntity.getAuthor(), bookEntity.getIsbn(), bookEntity.getPrice());
    }

    public BookDto delete(Long id) {
        BookEntity bookEntity = getById(id);
        bookRepository.deleteById(id);
        return new BookDto(bookEntity.getId(), bookEntity.getTitle(),
                bookEntity.getAuthor(), bookEntity.getIsbn(), bookEntity.getPrice());
    }

    public BookDto get(Long id) {
        BookEntity bookEntity = getById(id);
        return new BookDto(bookEntity.getId(), bookEntity.getTitle(),
                bookEntity.getAuthor(), bookEntity.getIsbn(), bookEntity.getPrice());
    }

    private void checkIsbn(String isbn) {
        Optional<BookEntity> optionalUser = bookRepository.findByIsbn(isbn);
        // check isbn unique
        if (optionalUser.isPresent()) {
            log.info("this isbn number already exist {}", optionalUser.get().getIsbn());
            throw new AppBadRequestException("isbn must be unique !!!");
        }
    }

    private BookEntity getById(Long id) {
        Optional<BookEntity> optionalUser = bookRepository.findById(id);
        if (optionalUser.isEmpty()) {
            log.info("Book not found : {}", id);
            throw new AppBadRequestException("book not found !!!");
        }
        return optionalUser.get();
    }
}
