package by.gsu.epamlab.exceptions;

public class DataSourceException extends Exception {
    // todo

    private String message;

    public DataSourceException(String message) {
        this.message = message;
    }

    public DataSourceException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return "DataSourceException{" +
                "message='" + message + '\'' +
                '}' + super.getCause();
    }
}
