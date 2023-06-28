package ac.uk.bolton.ecommercebackend.exception.custom;

public class UnmanagedException extends RuntimeException {
    public UnmanagedException(String error) {
        super(error);
    }
}
