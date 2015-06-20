package by.gsu.epamlab.model.dao;

import by.gsu.epamlab.model.beans.Note;
import by.gsu.epamlab.model.exceptions.DataSourceException;

import java.util.List;

public interface INoteDao {
    void create(Note note) throws DataSourceException;
    Note read(int noteId, String name) throws DataSourceException;
    void update(Note note) throws DataSourceException;
    void delete(Note note) throws DataSourceException;
    List<Note> getAll(int userId);
}
