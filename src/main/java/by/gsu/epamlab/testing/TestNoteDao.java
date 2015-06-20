package by.gsu.epamlab.testing;

import by.gsu.epamlab.model.beans.Note;
import by.gsu.epamlab.model.dao.INoteDao;
import by.gsu.epamlab.model.daoimpl.NoteDaoMemory;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class TestNoteDao {

    public static void main(String[] args) {
        INoteDao noteDao = new NoteDaoMemory();

        System.out.println(Arrays.toString(noteDao.getAll(1).toArray()));
        System.out.println();

        Note note1 = new Note();

        note1.setName("Complete stage 3");
        note1.setDateEnding(Date.valueOf(LocalDate.now().plusDays(3)));
        note1.setTimeEnding(Time.valueOf(LocalTime.now().plusHours(10)));
        note1.setUserId(1);
        note1.setDescription("Add usability");
        note1.setCompleted(false);
        note1.setDeleted(false);

        noteDao.create(note1);

        System.out.println(Arrays.toString(noteDao.getAll(1).toArray()));
        System.out.println();

        Note note = noteDao.read(note1.getUserId(), note1.getName());
        note.setDescription("Add usability and features!!");
        noteDao.update(note);

        System.out.println(Arrays.toString(noteDao.getAll(1).toArray()));
        System.out.println();

        noteDao.delete(note);

        System.out.println(Arrays.toString(noteDao.getAll(1).toArray()));
        System.out.println();
    }
}
