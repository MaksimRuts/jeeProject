package by.gsu.epamlab.model.daoimpl;

import by.gsu.epamlab.model.beans.Note;
import by.gsu.epamlab.model.dao.INoteDao;
import by.gsu.epamlab.model.exceptions.DataSourceException;
import by.gsu.epamlab.model.exceptions.ExceptionConstants;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteDaoMemory implements INoteDao {
//    private static Map<String, Note> notes;
    private static Map<Integer, Map<String, Note>> notes;

    static {
//        notes = new HashMap<String, Note>();
        notes = new HashMap<Integer, Map<String, Note>>();

        Note note1 = new Note();

        note1.setName("Complete stage 2");
        note1.setDateEnding(Date.valueOf(LocalDate.now().plusDays(1)));
        note1.setTimeEnding(Time.valueOf(LocalTime.now()));
        note1.setUserId(1);
        note1.setDescription("Add notes handling");
        note1.setCompleted(false);
        note1.setDeleted(false);

        notes.put(note1.getUserId(), new HashMap<String, Note>());
        notes.get(note1.getUserId()).put(note1.getName(), note1);
    }

    @Override
    public void create(Note note) throws DataSourceException {
        if (!notes.containsKey(note.getUserId())) {
            notes.put(note.getUserId(), new HashMap<String, Note>());
            notes.get(note.getUserId()).put(note.getName(), note);
        } else {
            if (!notes.get(note.getUserId()).containsKey(note.getName())) {
                notes.get(note.getUserId()).put(note.getName(), note);
            } else {
                throw new DataSourceException(ExceptionConstants.Messages.RECORD_ALREADY_EXIST);
            }
        }
    }

    @Override
    public Note read(int noteId, String name) throws DataSourceException {
        if (notes.containsKey(noteId) && notes.get(noteId).containsKey(name)) {
            return notes.get(noteId).get(name);
        } else {
            throw new DataSourceException(ExceptionConstants.Messages.RECORD_NOT_EXIST);
        }
    }

    @Override
    public void update(Note note) throws DataSourceException {
        if (notes.containsKey(note.getUserId()) &&
                notes.get(note.getUserId()).containsKey(note.getName())){
            notes.get(note.getUserId()).put(note.getName(), note);
        } else {
            throw new DataSourceException(ExceptionConstants.Messages.RECORD_UPDATE_ERROR);
        }
    }

    @Override
    public void delete(Note note) throws DataSourceException {
        try {
            if (notes.containsKey(note.getUserId())){
                if (!notes.get(note.getUserId()).remove(note.getName(), note)) {
                    throw new DataSourceException();
                }
            }
        } catch (Exception e) {
            throw new DataSourceException(ExceptionConstants.Messages.RECORD_DELETE_ERROR);
        }
    }

    @Override
    public List<Note> getAll(int userId) {
        return notes.containsKey(userId) ?
                new ArrayList<Note>(notes.get(userId).values()) : new ArrayList<Note>();
    }
}
