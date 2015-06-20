package by.gsu.epamlab.model.daoimpl;

import by.gsu.epamlab.model.beans.Note;
import by.gsu.epamlab.model.dao.INoteDao;
import by.gsu.epamlab.model.exceptions.DataSourceException;

import java.util.List;

public class NoteDaoDB implements INoteDao {
    @Override
    public void create(Note note) throws DataSourceException {

    }

    @Override
    public Note read(int noteId, String name) throws DataSourceException {
        return null;
    }

    @Override
    public void update(Note note) throws DataSourceException {

    }

    @Override
    public void delete(Note note) throws DataSourceException {

    }

    @Override
    public List<Note> getAll(int userId) {
        return null;
    }
}
