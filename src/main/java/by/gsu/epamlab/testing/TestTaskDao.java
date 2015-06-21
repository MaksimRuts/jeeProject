package by.gsu.epamlab.testing;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.dao.ITaskDao;
import by.gsu.epamlab.model.factories.AbstractDaoFactory;
import by.gsu.epamlab.model.factories.MemoryDaoFactory;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class TestTaskDao {

    public static void main(String[] args) {
        ITaskDao noteDao = AbstractDaoFactory.getFactory(MemoryDaoFactory.class).getTaskDao();

        System.out.println(Arrays.toString(noteDao.getAll(1).toArray()));
        System.out.println();

        Task task1 = new Task();

        task1.setName("Complete stage 3");
        task1.setDateEnding(Date.valueOf(LocalDate.now().plusDays(3)));
        task1.setTimeEnding(Time.valueOf(LocalTime.now().plusHours(10)));
        task1.setUserId(1);
        task1.setDescription("Add usability");
        task1.setCompleted(false);
        task1.setDeleted(false);

        noteDao.create(task1);

        System.out.println(Arrays.toString(noteDao.getAll(1).toArray()));
        System.out.println();

        Task task = noteDao.read(task1.getUserId(), task1.getName());
        task.setDescription("Add usability and features!!");
        noteDao.update(task);

        System.out.println(Arrays.toString(noteDao.getAll(1).toArray()));
        System.out.println();

        noteDao.delete(task);

        System.out.println(Arrays.toString(noteDao.getAll(1).toArray()));
        System.out.println();
    }
}
