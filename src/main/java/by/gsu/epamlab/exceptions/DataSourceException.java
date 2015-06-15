package by.gsu.epamlab.exceptions;

public class DataSourceException extends Exception {
    private String message;

    public DataSourceException(String message) {
        this.message = message;
    }

    public DataSourceException(String message, Throwable cause) {
        super(cause);
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(this.getClass().getSimpleName());
        builder.append('{');
        builder.append("message='").append(message);
        if (super.getCause() != null) {
            builder.append("; cause = ").append(super.getCause());
        }
        builder.append('}');
        return builder.toString();
    }
}
