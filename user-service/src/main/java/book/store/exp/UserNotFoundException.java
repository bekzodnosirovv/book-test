package book.store.exp;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) { // TODO handle it
        super(message);
    }
}
