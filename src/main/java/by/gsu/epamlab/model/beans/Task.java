package by.gsu.epamlab.model.beans;

import by.gsu.epamlab.model.exceptions.ValidationException;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Task implements Serializable {
    public static final String DATE_PARSE_PATTERN = "yyyy-MM-dd";

    private int id;
    private String name;
    private String description;
    private Date dateEnding;
    private boolean isCompleted;
    private boolean isDeleted;
    private int userId;
    private String filename;

    public Task() {
        setCompleted(false);
        setDeleted(false);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateEnding() {
        return dateEnding;
    }

    public void setDateEnding(Date dateEnding) {
        this.dateEnding = dateEnding;
    }

    public void setDateEnding(String dateEnding) {
        try {
            this.dateEnding = new Date((new SimpleDateFormat(DATE_PARSE_PATTERN)
                    .parse(dateEnding))
                    .getTime());
        } catch (ParseException e) {
            throw new ValidationException(dateEnding, e);
        }
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateEnding=" + dateEnding +
                ", isCompleted=" + isCompleted +
                ", isDeleted=" + isDeleted +
                ", userId=" + userId +
                '}';
    }
}
