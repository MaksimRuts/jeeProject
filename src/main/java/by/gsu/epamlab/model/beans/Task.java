package by.gsu.epamlab.model.beans;

import by.gsu.epamlab.model.exceptions.ValidationException;

import java.sql.Date;

public class Task {
    private int id;
    private String name;
    private String description;
    private Date dateEnding;
    private boolean isCompleted;
    private boolean isDeleted;
    private int userId;

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
            this.dateEnding = Date.valueOf(dateEnding);
        } catch (IllegalArgumentException e) {
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
