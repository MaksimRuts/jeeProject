package by.gsu.epamlab.model.beans;

import java.sql.Date;
import java.sql.Time;

public class Note {
    private int id;
    private String name;
    private String description;
    private Date dateEnding;
    private Time timeEnding;
    private boolean isCompleted;
    private boolean isDeleted;
    private int userId;

    public Note() {
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

    public Time getTimeEnding() {
        return timeEnding;
    }

    public void setTimeEnding(Time timeEnding) {
        this.timeEnding = timeEnding;
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
                ", timeEnding=" + timeEnding +
                ", isCompleted=" + isCompleted +
                ", isDeleted=" + isDeleted +
                ", userId=" + userId +
                '}';
    }
}
