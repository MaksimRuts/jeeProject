package by.gsu.epamlab.exceptions;

public class DataSourceException extends Exception {
    // todo

    private String message;

    public DataSourceException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DataSourceException{" +
                "message='" + message + '\'' +
                '}';
    }
}
