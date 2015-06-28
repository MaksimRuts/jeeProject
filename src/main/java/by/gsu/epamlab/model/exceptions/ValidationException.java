package by.gsu.epamlab.model.exceptions;

public class ValidationException extends RuntimeException {
    String value;

    public ValidationException(String value) {
        this.value = value;
    }

    public ValidationException(String value, Throwable cause) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ValidationException{" +
                "value='" + value + '\'' +
                '}';
    }
}
